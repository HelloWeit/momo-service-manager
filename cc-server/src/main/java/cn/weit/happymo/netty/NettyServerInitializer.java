package cn.weit.happymo.netty;

import cn.weit.happymo.handler.HeartbeatHandler;
import cn.weit.happymo.protobuf.HeartBeatInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author weitong
 */
@Component
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

	@Autowired
	private HeartbeatHandler heartbeatHandler;

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new ProtobufVarint32FrameDecoder());
		pipeline.addLast(new ProtobufDecoder(HeartBeatInfo.HeartBeat.getDefaultInstance()));
		pipeline.addLast(new IdleStateHandler(10, 0, 0));
		pipeline.addLast(heartbeatHandler);
	}

}
