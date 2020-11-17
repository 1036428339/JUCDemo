package com.interview.study.thread;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.*;

/**
 * @author liwenchang
 * @create 2020-11-09 22:53
 * 线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        //自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i = 0; i < 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void threadPoolInit() {
        //        System.out.println(Runtime.getRuntime().availableProcessors());
//        Array Arrays
//        Collection Collections
//        Executor Executors
        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池固定个处理线程 执行长期任务性能较好
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个处理线程 一个任务一个任务执行的
//        ExecutorService threadPool = Executors.newCachedThreadPool();//一池n个处理线程 可扩容的

        try {
            //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
