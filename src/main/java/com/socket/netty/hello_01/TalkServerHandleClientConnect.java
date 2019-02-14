package com.socket.netty.hello_01;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TalkServerHandleClientConnect extends SimpleChannelInboundHandler<Object> {

    // 客户端第一次链接时触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("连接成功！");
        super.channelActive(ctx);
    }

    // 读取客户端发送的数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收到客户端：" + ctx.channel().remoteAddress() + " 发送来的数据：" + msg);
        ctx.writeAndFlush("已接收到你发送来的数据：" + msg); // 向客户端发送数据
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("报错：" + cause);
    }
}