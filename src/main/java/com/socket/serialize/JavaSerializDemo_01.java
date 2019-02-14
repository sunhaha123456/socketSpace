package com.socket.serialize;

import lombok.Data;

import java.io.*;

/**
 * 功能：传统java 序列化/反序列化
 * @author sunpeng
 * @date 2018
 */
public class JavaSerializDemo_01 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UserInfoA userInfo = new UserInfoA();
        userInfo.setUserId(1);
        userInfo.setUserName("sunpeng");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(userInfo);
        os.close();
        byte[] arr = bos.toByteArray();
        System.out.println("序列化后字节长度：" + arr.length);

        ByteArrayInputStream bis = new ByteArrayInputStream(arr);
        ObjectInputStream oi = new ObjectInputStream(bis);
        Object obj = oi.readObject();
        UserInfoA objUserInfo = (UserInfoA)obj;
        oi.close();
    }
}

@Data
class UserInfoA implements Serializable {
    private Integer userId;
    private String userName;
}