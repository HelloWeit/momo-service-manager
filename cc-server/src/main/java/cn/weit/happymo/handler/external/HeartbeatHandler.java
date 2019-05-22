package cn.weit.happymo.handler.external;

import cn.weit.happymo.message.MoRequest.MoRequestMsg;
import cn.weit.happymo.message.MsgTypeEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static cn.weit.happymo.constant.Constants.MISS_PACKET_NUM;

/**
 * @author weitong
 */
@Component
@Slf4j
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    private int counter = 0;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MoRequestMsg) {
            MoRequestMsg packet = (MoRequestMsg) msg;
            if (packet.getMsgType().equals(MsgTypeEnum.MsgType.HEARTBEAT)) {
                ReferenceCountUtil.release(packet);
                return;
            }
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            if (counter >= MISS_PACKET_NUM) {
                counter = 0;
                ctx.channel().close().sync();
                log.info("Disconnected client {}", ctx.channel().remoteAddress());
            } else {
                counter++;
                log.info("Client:{} Miss:{} heart beat packet", ctx.channel().remoteAddress(), counter);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("connect exception", cause);
        ctx.close();
    }
}
