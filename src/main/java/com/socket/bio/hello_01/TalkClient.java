package com.socket.bio.hello_01;

import java.io.*;
import java.net.Socket;

// 同步阻塞 socket client
public class TalkClient {

    public static void main(String[] args) {
        objMsg();
    }

    public static void objMsg() {
        Socket s = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            String ip = "127.0.0.1";
            int port = 8080;
            s = new Socket(ip, port);

            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(new TalkServer_03.DemoTransObj("clientSendMsg-aaa", "clientSendMsg-bbb"));
            oos.flush();

            ois = new ObjectInputStream(s.getInputStream());
            TalkServer_03.DemoTransObj readObj = (TalkServer_03.DemoTransObj) ois.readObject();
            System.out.println("客户端收到的信息：" + readObj.getParam1() + "  " + readObj.getParam2());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void strMsg() {
        Socket s = null;
        BufferedWriter bw = null;
        BufferedReader br = null;
        try {
            String ip = "127.0.0.1";
            int port = 8080;
            s = new Socket(ip, port);
            bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bw.write("你好，我是客户端！\n");
            bw.flush();
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("客户端收到的信息：" + br.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
