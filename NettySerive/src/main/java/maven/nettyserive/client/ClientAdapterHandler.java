/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maven.nettyserive.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *
 * @author huongnt
 */
public class ClientAdapterHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.toString());
    }

//    @Override
//    public void messageReceived(ChannelHandlerContext chc, String t) throws Exception {
//        System.out.println(t);
//    }
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        String stringMessage = (String) msg;
//        //messageHandler.handler(ctx,  stringMessage + "}");
//        System.out.println("");
//    }
//
}
