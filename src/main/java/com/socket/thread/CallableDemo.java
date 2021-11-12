package com.socket.thread;

import java.util.concurrent.*;

class CallableDemo implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "OK";
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();

        //1、callable第一种使用方式
        Future<String> future = exec.submit(new CallableDemo());
        String res1 = future.get(2000, TimeUnit.MILLISECONDS); //同步等待结果，而且设置超时时间，注意此处一定要try捕获异常，不然会有问题
        System.out.println(res1);

        System.out.println("========================================");

        //2、callable第二种使用方式
        FutureTask<String> futureTask = new FutureTask(new CallableDemo());
        exec.execute(futureTask);
        String res2 = futureTask.get(2000, TimeUnit.MILLISECONDS); //同步等待结果，而且设置超时时间，注意此处一定要try捕获异常，不然会有问题
        System.out.println(res2);
    }
}