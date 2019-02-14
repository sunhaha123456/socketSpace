package com.socket.bio.hello_01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// 同步阻塞 socket demo
public class TalkServer {
    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket s = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            ss = new ServerSocket(7987);
            while(true){
                System.out.println("正在等待客户端请求！");
                s = ss.accept();
                if (s != null) {
                    System.out.println("已接受到客户端请求！");
                    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String recMsg = br.readLine();
                    System.out.println("读取到客户端发送来的信息：" + recMsg);
                    bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    bw.write("客户端啊，老哥已收到你的信息了！\n");
                    bw.flush();
                    s.close();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (ss != null) {
                    ss.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

