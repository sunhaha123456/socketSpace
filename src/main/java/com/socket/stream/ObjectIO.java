package com.socket.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;

public class ObjectIO {

    public static void main(String[] args) throws Exception {
        createObj();
        readObj();
    }

    // 写入对象
    public static void createObj() throws Exception {
        //1.创建目标路径
        File file = new File("D:\\aaa.txt");
        //2.创建流通道
        FileOutputStream fos = new FileOutputStream(file);
        //3.创建对象输出流
        ObjectOutputStream objOP = new ObjectOutputStream(fos);
        //4.创建类对象，并初始化
        DemoObj obj = new DemoObj("玛丽苏", "男", "18");
        //5.向目标路径文件写入对象
        objOP.writeObject(obj);
        //6.关闭资源
        objOP.close();
    }

    // 再读取对象
    public static void readObj() throws Exception {
        File file = new File("D:\\aaa.txt");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream objIP = new ObjectInputStream(fis);
        //读取对象数据，需要将对象流强制转换为 要写入对象的类型
        DemoObj obj = (DemoObj) objIP.readObject();
        System.out.println(obj.getParam1() + "  " + obj.getParam2() + "  " + obj.getParam3());
    }

    @AllArgsConstructor
    @Data
    public static class DemoObj implements Serializable {
        private String param1;
        private String param2;
        private String param3;
    }
}
