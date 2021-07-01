package com.socket.thread;

import java.util.concurrent.*;

class TaskWithResult implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "OK";
    }
}

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<String> st = exec.submit(new TaskWithResult());

        System.out.println(st.get(2000, TimeUnit.MILLISECONDS)); //同步等待结果，而且设置超时时间，注意此处一定要try捕获异常，不然会有问题
        System.out.println("over");
    }
}