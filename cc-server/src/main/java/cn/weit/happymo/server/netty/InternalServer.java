package cn.weit.happymo.server.netty;

import cn.weit.happymo.cache.MemCache;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Random;

/**
 * @author weitong
 */
@Component
public class InternalServer {
    @Value("${internal.server.netty.port}")
    private int internalPort;
    @Value("${internal.server.num}")
    private int num;
    private final Channel channel;
    private final Timer timer = new HashedWheelTimer();
    private final Random rng = new Random(System.currentTimeMillis());



    @Autowired
    private InternalServerInitializer internalServerInitializer;

    public InternalServer() throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(internalServerInitializer);
            ChannelFuture f = b.bind(internalPort).sync();
            this.channel = f.channel();
            channel.closeFuture().await();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }


    public void scheduleTask() {
        List<InetSocketAddress> randomNodes= MemCache.chooseSample(num);

    }




}
