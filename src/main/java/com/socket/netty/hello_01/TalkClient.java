package com.socket.netty.hello_01;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TalkClient {
    public void connect(String host, int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(new TalkClientInitHandle());
            Channel channel = b.connect(host, port).sync().channel();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            String line = null;
            while (true) {
                 line = in.readLine();
                if (line == null) {
                    continue;
                }else if (line.equals("close") || line.equals("关闭")) {
                    channel.disconnect();
                    return;
                }
                // 向服务端发送数据
                channel.writeAndFlush(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // 优雅退出，释放线程池资源
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new TalkClient().connect("127.0.0.1", 5555);
    }
}