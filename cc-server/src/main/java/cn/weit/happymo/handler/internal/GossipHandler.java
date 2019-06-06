package cn.weit.happymo.handler.internal;

import cn.weit.happymo.cache.ServiceCache;
import cn.weit.happymo.dto.HeartbeatInfo;
import cn.weit.happymo.dto.SyncApiInfo;
import cn.weit.happymo.message.MoRequest.MoRequestMsg;
import cn.weit.happymo.server.netty.InternalServer;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetSocketAddress;

import static cn.weit.happymo.message.ServerState.State;

/**
 * @author weitong
 */
@Slf4j
public class GossipHandler extends SimpleChannelInboundHandler<AddressedEnvelope<MoRequestMsg, InetSocketAddress>> {

    private InternalServer internalServer;

    @Autowired
    private ServiceCache serviceCache;

    public GossipHandler(InternalServer internalServer) {
        this.internalServer = internalServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, AddressedEnvelope<MoRequestMsg, InetSocketAddress> msg) throws Exception {
        final InetSocketAddress sender = msg.sender();
        MoRequestMsg moRequestMsg = msg.content();
        switch (moRequestMsg.getMsgType()) {
            case GOSSIP_PING:
                handlePingMsg(HeartbeatInfo.convert(moRequestMsg));
                break;
            case GOSSIP_SYNC:
                handleSyncMsg(sender, SyncApiInfo.convert(moRequestMsg));
                break;
            case GOSSIP_REQ:
                handlerReqMsg(SyncApiInfo.convert(moRequestMsg));
                break;
            default:
                log.error("Unknown message: {}", msg.content());
        }
    }

    private void handlerReqMsg(SyncApiInfo syncApiInfo) {
        serviceCache.addApi(syncApiInfo);
    }

    private void handlePingMsg(HeartbeatInfo heartbeatInfo) {
        serviceCache.addNode(heartbeatInfo);
    }

    private void handleSyncMsg(InetSocketAddress sender, SyncApiInfo syncApiInfo) {
        if (syncApiInfo.getState().equals(State.Alive)) {
            long currentTime = serviceCache.getApiTime(syncApiInfo);
            if (syncApiInfo.getUpdateTime() > currentTime) {
                serviceCache.addApi(syncApiInfo);
            }
            final Channel channel = internalServer.getChannel();
            if (syncApiInfo.getUpdateTime() < currentTime) {
                SyncApiInfo syncApiInfo1 = serviceCache.getApi(syncApiInfo);
                if (syncApiInfo1 != null) {
                    channel.writeAndFlush(
                            new DefaultAddressedEnvelope<>(
                                    SyncApiInfo.convert(syncApiInfo1),
                                    sender, channel.localAddress())
                    );
                }
            }
            return;
        }
        serviceCache.delApi(syncApiInfo);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("",cause);
        ctx.close();
    }
}
