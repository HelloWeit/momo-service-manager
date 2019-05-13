package cn.weit.happymo.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
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
@Component("externalServer")
@Slf4j
public class ExternalServer {
    @Value("${external.server.netty.port}")
    private int externalPort;

    @Autowired
    private ExternalServerInitializer externalServerInitializer;

    void start() {
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
        server.group(mainGroup, subGroup).channel(NioServerSocketChannel.class)
                .childHandler(externalServerInitializer);
        ChannelFuture future = server.bind(externalPort);
        future.addListener((ChannelFutureListener) future1 -> {
            if(future1.isSuccess()){
                log.info("Netty server started");
            }else{
                log.error("server start failed");
                future1.cause().printStackTrace();
            }
        });
    }

}
