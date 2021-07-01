package com.socket.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    private Lock lock = new ReentrantLock();

    public void show(String name) {
        lock.lock(); // 获取锁
        try {
            System.out.println(name + " get the lock");
            // 访问此锁保护的资源
        } finally {
            lock.unlock(); // 释放锁
            System.out.println(name + " release the lock");
        }
    }

    public static void main(String[] args) {
        while (true) {
            LockDemo ld = new LockDemo();
            new Thread(() -> ld.show("A")).start();
            new Thread(() -> ld.show("B")).start();
        }
    }
}