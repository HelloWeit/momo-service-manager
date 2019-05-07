package cn.weit.happymo.netty;

import cn.weit.happymo.handler.MoResponseHandler;
import cn.weit.happymo.message.MoResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author weitong
 */
@Slf4j
public class MoClient {

    private String host;
    private int port;

    public MoClient(String host, int port) {
        this.port = port;
        this.host = host;
    }
    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true);
            b.remoteAddress(new InetSocketAddress(host, port));
            b.handler(new ChannelInitializer<SocketChannel>() {
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                    ch.pipeline().addLast(new ProtobufDecoder(MoResponse.MoResponseMsg.getDefaultInstance()));
                    ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                    ch.pipeline().addLast(new ProtobufEncoder());
                    ch.pipeline().addLast(new MoResponseHandler());
                }
            });
            ChannelFuture f = b.connect().sync();
            f.addListener((ChannelFutureListener) future -> {
                if(future.isSuccess()){
                    log.info("client connected");
                }else{
                    log.error("server attemp failed");
                    future.cause().printStackTrace();
                }

            });
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
