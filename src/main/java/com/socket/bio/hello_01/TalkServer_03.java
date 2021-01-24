package com.socket.bio.hello_01;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// 同步阻塞 socket demo
public class TalkServer_03 {

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
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;

            try {
                System.out.println("已接受到客户端请求！");

                ois = new ObjectInputStream(socket.getInputStream());
                DemoTransObj msgObj = (DemoTransObj) ois.readObject();
                System.out.println("读取到客户端发送来的信息：" + msgObj.getParam1() + "  " + msgObj.getParam2());

                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(new DemoTransObj("server-aaa", "server-bbb"));
                oos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    ois.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    oos.close();
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

    @AllArgsConstructor
    @Data
    public static class DemoTransObj implements Serializable {
        private String param1;
        private String param2;
    }
}
