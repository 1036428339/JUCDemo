package com.interview.study.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author liwenchang
 * @create 2020-11-03 20:17
 * 火箭发射 倒计时
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+ " 国，被灭 ");
                countDownLatch.countDown();//计数减1
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();//计数减到0 通过
        System.out.println(Thread.currentThread().getName() + "秦一统华夏");

        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetMessage());
    }

    private static void closwDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+ " 工作结束 ");
                countDownLatch.countDown();//计数减1
            },String.valueOf(i)).start();
        }
        countDownLatch.await();//计数减到0 通过
        System.out.println(Thread.currentThread().getName() + "最后离开");
    }
}
