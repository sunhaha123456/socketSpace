package com.socket.thread;

public class ThreadJoinDemo extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("1");
        ThreadJoinDemo thread1 = new ThreadJoinDemo();
        thread1.start();
        // 要在start之后调用join，调用join后，父线程会阻塞等待子线程执行完毕后，再继续执行
        thread1.join();
        System.out.println("2");
    }
}