package com.socket.netty.hello_05_MessagePackga;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class TalkClient {
    public void connect(String host, int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).
                    option(ChannelOption.TCP_NODELAY, true).
                    option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000).
                    handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                            pipeline.addLast(new MsgpackDecoder());
                            pipeline.addLast(new LengthFieldPrepender(2));
                            pipeline.addLast(new MsgpackEncoder());
                            pipeline.addLast(new TalkClientHandle());
                        }
                    });

            ChannelFuture future = b.connect(host, port).sync();
            Channel channel = future.channel();
            for (int x = 0; x < 100; x++) {
                channel.writeAndFlush(new UserInfo(1, "123"));
            }
            channel.writeAndFlush(123);
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