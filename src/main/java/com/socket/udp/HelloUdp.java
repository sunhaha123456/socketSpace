package com.socket.udp;

import java.net.*;

class UdpServer {
    // 定义一些常量
    private final int PORT_NUM   = 5066; // port号
    private final int MAX_LENGTH = 1024; // 最大接收字节长度
    private byte[] receMsgs = new byte[MAX_LENGTH]; // 用以存放接收数据的字节数组
    private DatagramSocket datagramSocket; // 数据报套接字
    private DatagramPacket datagramPacket; // 用以接收数据报

    public UdpServer(){
        try {
            // 创建一个数据报套接字，并将其绑定到指定port上
            datagramSocket = new DatagramSocket(PORT_NUM);
            datagramPacket = new DatagramPacket(receMsgs, receMsgs.length);
            // receive()来等待接收UDP数据报
            datagramSocket.receive(datagramPacket);

            /****** 解析数据报****/
            String receStr = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());
            System.out.println("接收到的数据报内容：" + receStr);

            /***** 返回ACK消息数据报*/
            // 组装数据报
            byte[] buf = "我已经接收到你发的信息了！".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, datagramPacket.getAddress(), datagramPacket.getPort());
            datagramSocket.send(sendPacket); // 发送消息
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // 关闭socket
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    public static void main(String[] args) {
        new UdpServer();
    }
}

class UdpClient {

    private String HOST = "127.0.0.1";
    private final int PORT_NUM = 5066;

    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    public UdpClient(){
        try {
            datagramSocket = new DatagramSocket();
            byte[] buf = "我是客户端发送的报文内容".getBytes();
            InetAddress address = InetAddress.getByName(HOST);
            datagramPacket = new DatagramPacket(buf, buf.length, address, PORT_NUM);
            datagramSocket.send(datagramPacket); // 发送数据

            byte[] receBuf = new byte[1024];
            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            datagramSocket.receive(recePacket);

            String receStr = new String(recePacket.getData(), 0 , recePacket.getLength());
            System.out.println("客户端接收到的信息：" + receStr);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // 关闭socket
            if(datagramSocket != null){
                datagramSocket.close();
            }
        }
    }

    public static void main(String[] args) {
        new UdpClient();
    }
}