package com.socket.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    private Lock lock = new ReentrantLock(); //默认是创建一把非公平锁

    public void show(String name) {
        lock.lock(); //获取锁，如果未获取到锁，会一直等待
        try {
            System.out.println(name + " get the lock");
            //访问此锁保护的资源
        } finally {
            lock.unlock(); //释放锁
            System.out.println(name + " release the lock");
        }
    }

    public static void main(String[] args) {
        LockDemo ld = new LockDemo();

        List<Thread> threadList = new ArrayList<Thread>();
        threadList.add(new Thread(() -> ld.show(1 + "")));
        threadList.add(new Thread(() -> ld.show(2 + "")));
        threadList.add(new Thread(() -> ld.show(3 + "")));
        threadList.add(new Thread(() -> ld.show(4 + "")));
        threadList.add(new Thread(() -> ld.show(5 + "")));
        threadList.add(new Thread(() -> ld.show(6 + "")));
        threadList.add(new Thread(() -> ld.show(7 + "")));
        threadList.add(new Thread(() -> ld.show(8 + "")));
        threadList.add(new Thread(() -> ld.show(9 + "")));
        threadList.add(new Thread(() -> ld.show(10 + "")));

        for (Thread t : threadList) {
            t.start();
        }
    }
}