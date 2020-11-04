package com.interview.study.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author liwenchang
 * @create 2020-11-03 20:54
 * 集齐7颗龙珠召唤神龙
 * CyclicBarrier字面意思可循环（Cyclic）使用的屏障（Barrier）,它要做的事情是
 * 让一组线程到达一个屏障（也叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会
 * 开门，所有被屏障拦截的线程才会继续干活，线程进入屏障通过CyclicBarrier的awite()方法
 * */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });

        for (int i = 0; i < 7; i++) {
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 获得第"+temp+"颗龙珠 ");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
