package com.interview.study.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author lwc
 * @date 2020/11/4 8:52
 * 抢车位
 * Semaphore 信号量
 * 主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个是用于并发线程数的控制
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位
        for (int i = 0; i <6; i++) {//模拟车辆
            new Thread(() -> {
                try {
                    semaphore.acquire();//抢到车位
                    System.out.println(Thread.currentThread().getName() +" 抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() +" 离开车位");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放车位
                }
            }, String.valueOf(i)).start();
        }
    }
}

