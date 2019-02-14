package com.socket.nio.hello_01;

import java.util.concurrent.CountDownLatch;

public class TalkServer {

    public static void main(String[] args) throws InterruptedException {
		int port = 8080;
        TalkServerThread talkServerHandleThread = new TalkServerThread(port);
        talkServerHandleThread.start();
        //Thread.sleep(10000);
        //talkServerHandleThread.serverStop();
        //Thread.sleep(10000);
        CountDownLatch cdl = new CountDownLatch(1);
        cdl.await();
    }
}