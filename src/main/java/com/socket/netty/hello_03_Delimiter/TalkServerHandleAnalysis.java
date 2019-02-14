package com.socket.netty.hello_03_Delimiter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TalkServerHandleAnalysis extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("连接成功！$_");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收到客户端：" + ctx.channel().remoteAddress() + " 发送来的数据：" + msg);
        ctx.writeAndFlush("已接收到你发送来的数据：" + msg + "$_"); // 向客户端发送数据
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("报错：" + cause);
        ctx.close();
    }
}