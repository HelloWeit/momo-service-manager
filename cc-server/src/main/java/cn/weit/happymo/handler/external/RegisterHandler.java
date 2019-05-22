package cn.weit.happymo.handler.external;

import cn.weit.happymo.cache.ServiceCache;
import cn.weit.happymo.dto.RegisterInfo;
import cn.weit.happymo.dto.SyncApiInfo;
import cn.weit.happymo.dto.SyncServerInfo;
import cn.weit.happymo.message.MoRequest;
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
            //todo 清理掉该服务下所有的api 需要把服务名和服务下apikey做个映射
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
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        serviceCache.putChannel(insocket, ctx.channel());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        serviceCache.removeChannel(insocket);
        //todo udpclient 随机选择内部节点汇报
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
