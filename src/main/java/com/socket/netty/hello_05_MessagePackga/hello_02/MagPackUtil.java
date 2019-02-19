package com.socket.netty.hello_05_MessagePackga.hello_02;

import org.msgpack.MessagePack;

import java.io.IOException;

class MagPackUtil {

    public static MessagePack magPackUserInfo = new MessagePack();

    static {
        magPackUserInfo.register(UserInfo.class);
    }

    public static byte[] writeUserInfo(Object msg) throws IOException {
        return magPackUserInfo.write(msg);
    }

    public static UserInfo readUserInfo(byte[] raw) throws IOException {
        return magPackUserInfo.read(raw, UserInfo.class);
    }
}