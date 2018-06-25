/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maven.nettyserive.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 *
 * @author huongnt
 */
public class ServerAdapterHandler extends ChannelInboundHandlerAdapter {

//    private static final EventExecutor EXECUTOR = new EventExecutor
    
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    //    private static final ChannelGroup channels = new DefaultChannelGroup();

//
//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        Channel incoming = ctx.channel();
//        for (Channel channel : channels) {
//
//            channel.write("[Server] - " + incoming.remoteAddress() + "has joined \n");
//        }
//        channels.add(ctx.channel());
//    }
//
//    @Override
//    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        Channel incoming = ctx.channel();
//        for (Channel channel : channels) {
//
//            channel.write("[Server] - " + incoming.remoteAddress() + "has left \n");
//        }
//        channels.remove(ctx.channel());
//    }
//
//    @Override
//    public void messageReceived(ChannelHandlerContext chc, String t) throws Exception {
//        Channel incoming = chc.channel();
//
//        for (Channel channel : channels) {
//            if (channel != incoming) {
//                channel.write("[" + incoming.remoteAddress() + "]" + t + "\n");
//            }
//        }
//    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Channel incoming = ctx.channel();

        for (Channel channel : channels) {
            if (channel != incoming) {
                channel.write("[" + incoming.remoteAddress() + "]" + msg.toString() + "\n");
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {

            channel.write("[Server] - " + incoming.remoteAddress() + "has left \n");
        }
        channels.remove(ctx.channel());
    }

//    @Override
//    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        Channel incoming = ctx.channel();
//        for (Channel channel : channels) {
//
//            channel.write("[Server] - " + incoming.remoteAddress() + "has joined \n");
//        }
//        channels.add(ctx.channel());
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // closed on shutdown.
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {

            channel.write("[Server] - " + incoming.remoteAddress() + "has joined \n");
        }
        channels.add(ctx.channel());
        super.channelActive(ctx);
    }

}
