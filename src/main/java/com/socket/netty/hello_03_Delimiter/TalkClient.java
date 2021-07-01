package com.socket.netty.hello_03_Delimiter;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class TalkClient {
    public void connect(String host, int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).
                    handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$_".getBytes()))); //maxFrameLength表示客户端接受服务端返回报文的字节最大数
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new TalkClientHandle());
                        }
                    });

            ChannelFuture future = b.connect(host, port).sync();
            Channel channel = future.channel();
            for (int x = 0; x < 1000; x++) {
                channel.writeAndFlush("我是客户端信息：" + x + "$_");
            }
            channel.closeFuture().sync();
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