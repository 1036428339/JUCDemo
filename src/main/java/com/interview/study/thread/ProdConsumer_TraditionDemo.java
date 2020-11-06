package com.interview.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lwc
 * @date 2020/11/6 14:28
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 *  1 线程   操作(方法)    资源类
 *  2 判断   干活    通知
 */
class ShareData{ //资源类
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment()throws Exception{//++
        lock.lock();
        try {
            //1判断
            while (number != 0){
                //等待 不生产
                condition.await();
            }
            //2干活
            number++;
            System.out.println(Thread.currentThread().getName() + " "+number);
            //3防止虚假唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement()throws Exception{//--
        lock.lock();
        try {
            //1判断
            while (number == 0){
                //等待 不消费
                condition.await();
            }
            //2干活
            number--;
            System.out.println(Thread.currentThread().getName() + " "+number);
            //3防止虚假唤醒
            condition.signalAll();
//            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
