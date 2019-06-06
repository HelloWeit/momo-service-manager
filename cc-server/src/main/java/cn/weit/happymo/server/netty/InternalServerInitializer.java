package cn.weit.happymo.server.netty;

import cn.weit.happymo.handler.internal.GossipHandler;
import cn.weit.happymo.message.MoRequest;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.DatagramChannel;
import io.netty.handler.codec.DatagramPacketDecoder;
import io.netty.handler.codec.DatagramPacketEncoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import lombok.Setter;

/**
 * @author weitong
 */
public class InternalServerInitializer extends ChannelInitializer<DatagramChannel> {

    @Setter
    private InternalServer internalServer;

    public InternalServerInitializer(InternalServer internalServer) {
        this.internalServer = internalServer;
    }

    @Override
    protected void initChannel(DatagramChannel ch) throws Exception {
        ch.pipeline().addLast(new DatagramPacketDecoder(new ProtobufDecoder(MoRequest.MoRequestMsg.getDefaultInstance())));
        ch.pipeline().addLast(new DatagramPacketEncoder<>(new ProtobufEncoder()));
        ch.pipeline().addLast(new GossipHandler(internalServer));
    }
}
