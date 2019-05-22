package cn.weit.happymo.handler.external;

import cn.weit.happymo.cache.ServiceCache;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        serviceCache.putChannel(insocket, ctx.channel());
        //todo udpclient 随机选择内部节点汇报
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
