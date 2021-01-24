package com.socket.bio.hello_01;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// 同步阻塞 socket demo
public class TalkServer_02 {

    public static Executor executor = Executors.newFixedThreadPool(10);

    // 普通 BIO + Thread Pool
    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket s = null;
        try {
            ss = new ServerSocket(8080);
            while(true){
                System.out.println("正在等待客户端请求！");
                s = ss.accept();
                if (s != null) {
                    executor.execute(new SocketThread(s));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ss != null) {
                    ss.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @AllArgsConstructor
    @Data
    public static class SocketThread extends Thread {

        private Socket socket;

        @Override
        public void run() {
            BufferedReader br = null;
            BufferedWriter bw = null;

            try {
                System.out.println("已接受到客户端请求！");
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String recMsg = br.readLine();
                System.out.println("读取到客户端发送来的信息：" + recMsg);
                bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bw.write("客户端啊，老哥已收到你的信息了！\n");
                bw.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
