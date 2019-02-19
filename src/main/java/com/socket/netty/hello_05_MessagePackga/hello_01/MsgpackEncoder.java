package com.socket.netty.hello_05_MessagePackga.hello_01;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

public class MsgpackEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) throws Exception {
        MessagePack msgpack = new MessagePack();
        byte[] raw = msgpack.write(msg);
        byteBuf.writeBytes(raw);
    }
}