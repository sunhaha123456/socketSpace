package com.socket.nio.hello_01;

public class TalkClient {

    public static void main(String[] args) {
        String host = "127.0.0.1";
		int port = 8080;
		new TalkClientHandleThread(host, port).start();
    }
}