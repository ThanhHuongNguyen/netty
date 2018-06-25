/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maven.nettyserive.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
/**
 *
 * @author huongnt
 */
public class ClientAdapterInitializer extends ChannelInitializer<SocketChannel> {

    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast("fromer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        pipeline.addLast("handler", new ClientAdapterHandler());

//        ChannelPipeline pipeline = channel.pipeline();

        //pipeline.addLast(new DelimiterBasedFrameDecoder(1024*1024, Unpooled.copiedBuffer("}".getBytes("UTF-8"))));
//        pipeline.addLast(DECODER);
//        pipeline.addLast(ENCODER);
//
//        pipeline.addLast("idleStateHandler", new IdleStateHandler(10, 5, 0));
//        pipeline.addLast("tcpServerHandler", new ClientAdapterHandler());
    }

}
