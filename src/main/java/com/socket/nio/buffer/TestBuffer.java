package com.socket.nio.buffer;

import java.nio.ByteBuffer;

/*
 * 功能 ： 演示Java新IO中的Buffer
 * capacity：指定了可以存储在缓冲区中的最大数据容量，实际上，它指定了底层数组的大小
 * limit：结束位置
 * position：指定了下一个将要被写入或者读取的元素索引
 * */
public class TestBuffer {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(10);
        System.out.println("容量：" + bb.capacity());
        System.out.println("限制：" + bb.limit());
        System.out.println("当前位置："+bb.position());
        System.out.println("--------------------------------");
        bb.put((byte)10);
        System.out.println("容量：" + bb.capacity());
        System.out.println("限制：" + bb.limit());
        System.out.println("当前位置："+bb.position());
        System.out.println("--------------------------------");
        //反转此缓冲区。首先将限制设置为当前位置，然后将位置设置为 0
        bb.flip();
        System.out.println("容量：" + bb.capacity());
        System.out.println("限制：" + bb.limit());
        System.out.println("当前位置："+bb.position());
        System.out.println("--------------------------------");
        bb.put((byte)100);
        System.out.println("容量：" + bb.capacity());
        System.out.println("限制：" + bb.limit());
        System.out.println("当前位置：" + bb.position());
        System.out.println("--------------------------------");
        bb.clear();
        System.out.println("容量：" + bb.capacity());
        System.out.println("限制：" + bb.limit());
        System.out.println("当前位置：" + bb.position());
    }
}