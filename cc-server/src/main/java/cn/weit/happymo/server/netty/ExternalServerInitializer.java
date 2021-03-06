package cn.weit.happymo.server.netty;

import cn.weit.happymo.handler.external.HeartbeatHandler;
import cn.weit.happymo.handler.external.RegisterHandler;
import cn.weit.happymo.message.MoRequest;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author weitong
 */
public class ExternalServerInitializer extends ChannelInitializer<SocketChannel> {

	@Autowired
	private HeartbeatHandler heartbeatHandler;

	@Autowired
	private RegisterHandler registerHandler;

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new ProtobufVarint32FrameDecoder());
		pipeline.addLast(new ProtobufDecoder(MoRequest.MoRequestMsg.getDefaultInstance()));
		pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
		pipeline.addLast(new ProtobufEncoder());
		pipeline.addLast(new IdleStateHandler(10, 0, 0));
		pipeline.addLast(heartbeatHandler);
		pipeline.addLast(registerHandler);
	}

}
