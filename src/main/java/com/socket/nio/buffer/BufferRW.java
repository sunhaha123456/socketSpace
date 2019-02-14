package com.socket.nio.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// 文件复制
public class BufferRW {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileChannel fc1 = null;
        FileOutputStream fos = null;
        FileChannel fc2 = null;
        try {
            fis = new FileInputStream("E:/fis");
            fc1 = fis.getChannel();
            fos = new FileOutputStream("E:/fos");
            fc2 = fos.getChannel();

            ByteBuffer bb = ByteBuffer.allocate(10);
            while(fc1.read(bb)!=-1) {
                bb.flip();
                fc2.write(bb);
                bb.clear();
            }
            fc2.close();
            fos.close();
            fc1.close();
            fis.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                fc2.close();
                fos.close();
                fc1.close();
                fis.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}