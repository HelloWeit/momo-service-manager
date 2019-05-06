package cn.weit.happymo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author weitong
 */
@Component
@Slf4j
public class NettyServer {
    @Value("${netty.server.port}")
    private int port;

    @Autowired
    private NettyServerInitializer nettyServerInitializer;

    private ServerBootstrap server;
    private ChannelFuture future;

    public NettyServer() {
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(nettyServerInitializer);
    }


    void start() {
        this.future = server.bind(port);
        log.info("Netty server started");
    }

}
