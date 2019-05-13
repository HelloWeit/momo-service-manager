package cn.weit.happymo.server.netty;

import cn.weit.happymo.cache.ServiceCache;
import cn.weit.happymo.dto.HeartbeatInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static cn.weit.happymo.constant.Constants.TASK_DURATION;
import static cn.weit.happymo.constant.Constants.TASK_SLOT;

/**
 * @author weitong
 */
@Component("internalServer")
public class InternalServer {
    @Value("${internal.server.netty.port}")
    private int port;
    @Value("${internal.server.num}")
    private int num;
    @Value("$(server.name")
    private String serverName;
    @Value("$(server.ip")
    private String ip;
    @Autowired
    private InternalServerInitializer internalServerInitializer;
    @Autowired
    private ServiceCache serviceCache;
    @Getter
    private final Channel channel;
    private final Timer timer = new HashedWheelTimer(TASK_DURATION, TimeUnit.SECONDS, TASK_SLOT);

    public InternalServer() throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(internalServerInitializer);
            ChannelFuture f = b.bind(port).sync();
            this.channel = f.channel();
            channel.closeFuture().await();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }


    void scheduleTask() {
        List<InetSocketAddress> randomNodes= serviceCache.chooseSample(num);
        TimerTask task = timeout -> randomNodes.forEach(intSocketAddress -> channel.writeAndFlush(
                new DefaultAddressedEnvelope<>(
                        HeartbeatInfo.convert(serverName, ip, port),
                        intSocketAddress,
                        channel.localAddress()
                )
        ));
        timer.newTimeout(task, TASK_DURATION * TASK_SLOT + 1,TimeUnit.SECONDS);
    }




}
