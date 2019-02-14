package com.socket.serialize;

import lombok.Data;
import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * 功能：MessagePack 序列化/反序列化
 * @author sunpeng
 * @date 2018
 */
public class JavaSerializDemo_02_MessagePack {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UserInfoB userInfo = new UserInfoB();
        userInfo.setUserId(1);
        userInfo.setUserName("sunpeng");
        MessagePack magPack = new MessagePack();
        magPack.register(UserInfoB.class);
        byte[] raw = magPack.write(userInfo);
        System.out.println(raw.length);

        UserInfoB userInfoObj = magPack.read(raw, UserInfoB.class);
        System.out.println(userInfoObj.getUserId());
        System.out.println(userInfoObj.getUserName());
    }
}

@Data
class UserInfoB {
    private Integer userId;
    private String userName;
}