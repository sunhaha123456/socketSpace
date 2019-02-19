package com.socket.netty.hello_05_MessagePackga.hello_02;

import org.msgpack.MessagePack;

class MagPackUtil {

    public static MessagePack magPackUserInfo = new MessagePack();

    static {
        magPackUserInfo.register(UserInfo.class);
    }
}