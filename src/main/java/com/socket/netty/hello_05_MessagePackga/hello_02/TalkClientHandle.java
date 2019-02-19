package com.socket.netty.hello_05_MessagePackga.hello_02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.msgpack.MessagePack;

public class TalkClientHandle extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //System.out.println("server say:" + msg);
        if (msg != null) {
            try {
                System.out.println("接收到服务端：" + ctx.channel().remoteAddress() + "，发送来的数据：" + msg);
                MessagePack magPack = new MessagePack();
                magPack.register(UserInfo.class);
                byte[] raw = magPack.write(msg);
                UserInfo userInfo = magPack.read(raw, UserInfo.class);

                System.out.println("解析服务端发送来的数据：userId=" + userInfo.getUserId() + ",userName=" + userInfo.getUserName());

            } catch (Exception e) {
                System.out.println("接收到服务端：" + ctx.channel().remoteAddress() + "，发送来的数据：" + msg + "，解析报错，错误信息：" + e);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("报错：" + cause);
        ctx.close();
    }
}