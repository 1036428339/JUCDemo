package com.interview.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author liwenchang
 * @create 2020-11-09 21:59
 */
//class MyThread implements Runnable{
//    @Override
//    public void run() {
//
//    }
//}
//带返回的Thread
class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"comi in Callable");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //两个线程 main futureTask
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        //多个线程用一个futureTask 只有一个线程进去
        Thread t1 = new Thread(futureTask,"A");
//        Thread t2 = new Thread(futureTask,"B");
        t1.start();
//        t2.start();
        int result1  = 10;
        System.out.println(Thread.currentThread().getName());
//        //当算完立刻取
//        while (!futureTask.isDone()){
//
//        }
        int result2 =  futureTask.get();//Callable线程的计算结果，如果没有计算完成就要去强求，会导致线程堵塞 建议放在最后
        System.out.println(result1+result2);
    }
}
