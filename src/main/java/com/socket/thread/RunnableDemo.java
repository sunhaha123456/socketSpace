package com.socket.thread;

public class RunnableDemo implements Runnable {

    public void run() {
        System.out.println("哈哈哈");
    }

    public static void main(String[] args) {
        Thread a = new Thread(new RunnableDemo());
        a.start();
    }
}