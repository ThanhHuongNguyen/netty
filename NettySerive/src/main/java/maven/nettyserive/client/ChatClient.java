/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maven.nettyserive.client;

/**
 *
 * @author huongnt
 */
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatClient {

    private final String server;
    private final int port;

    public ChatClient(String server, int port) {
        this.server = server;
        this.port = port;
    }

    public static void main(String[] args) {
        String server = "localhost";
        int port = 8000;
        new ChatClient(server, port).start();
    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap().group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientAdapterInitializer());

            Channel channel = bootstrap.connect(server, port).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                channel.write(in.readLine() + "\r\n");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            group.shutdownGracefully();
        }
    }
}
