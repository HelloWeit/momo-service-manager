package cn.weit.happymo.handler.external;

import cn.weit.happymo.cache.ServiceCache;
import cn.weit.happymo.dto.RegisterInfo;
import cn.weit.happymo.dto.SyncApiInfo;
import cn.weit.happymo.dto.SyncServerInfo;
import cn.weit.happymo.message.MoRequest.MoRequestMsg;
import cn.weit.happymo.message.MsgTypeEnum;
import cn.weit.happymo.message.ServerState;
import cn.weit.happymo.server.netty.InternalServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author weitong
 */
@Component
public class RegisterHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private ServiceCache serviceCache;
    @Autowired
    private InternalServer internalServer;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MoRequestMsg) {
            MoRequestMsg packet = (MoRequestMsg) msg;
            if (packet.getMsgType().equals(MsgTypeEnum.MsgType.SERVER)) {
                doServerMsg(packet);
            } else {
                doApiMsg(packet);
            }
            ReferenceCountUtil.release(packet);
        }
    }

    private void doServerMsg(MoRequestMsg moRequestMsg) {
        RegisterInfo registerInfo = RegisterInfo.convert(moRequestMsg);
        if (moRequestMsg.getState().equals(ServerState.State.Alive)) {
            serviceCache.addServer(registerInfo);
        } else {
            serviceCache.delServer(registerInfo);
        }
        SyncServerInfo syncServerInfo = SyncServerInfo.convert(moRequestMsg);
        internalServer.randomSendMsg(SyncServerInfo.convert(syncServerInfo));
    }

    private void doApiMsg(MoRequestMsg moRequestMsg) {
        RegisterInfo registerInfo = RegisterInfo.convert(moRequestMsg);
        SyncApiInfo syncApiInfo = SyncApiInfo.convert(registerInfo);
        if (moRequestMsg.getState().equals(ServerState.State.Alive)) {
            serviceCache.addApi(syncApiInfo);
        } else {
            serviceCache.delApi(syncApiInfo);
        }
        internalServer.randomSendMsg(SyncApiInfo.convert(syncApiInfo));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        serviceCache.putChannel(inetSocketAddress, ctx.channel());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        serviceCache.removeChannel(inetSocketAddress);
        // 通知内部节点有client掉线了
        SyncServerInfo syncServerInfo = SyncServerInfo.convert(inetSocketAddress, serviceCache.getServerName(inetSocketAddress), ServerState.State.Dead);
        internalServer.randomSendMsg(SyncServerInfo.convert(syncServerInfo));
        // todo 通知关注该client的外部client 该client掉线了
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
