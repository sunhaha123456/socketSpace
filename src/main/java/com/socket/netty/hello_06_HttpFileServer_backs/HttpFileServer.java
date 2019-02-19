package com.socket.netty.hello_06_HttpFileServer_backs;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {

    public void run(final int port, final String url) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast("http-decoder", new HttpRequestDecoder()); // 请求消息解码器
						ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));// 目的是将多个消息转换为单一的request或者response对象
						ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());//响应解码器
						ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());//目的是支持异步大文件传输（）
						ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));// 业务逻辑
					}
				});
			ChannelFuture future = b.bind(port).sync();
			future.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
    }

    public static void main(String[] args) throws Exception {
		int port = 8080;
		String url = "/src/main/java/com/socket/";
		new HttpFileServer().run(port, url);
	}
}