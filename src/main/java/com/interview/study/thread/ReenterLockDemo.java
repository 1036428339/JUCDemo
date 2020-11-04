package com.interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liwenchang
 * @create 2020-11-01 22:18
 * 可重入锁 synchronized/ReentrantLock 是典型的可重入锁
 * 同一线程外层函数获得锁之后，内层递归函数仍然能获得该锁的代码
 * 也就是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块
 */
class Phone implements Runnable{
    public synchronized void sendSMS() throws  Exception{
        System.out.println(Thread.currentThread().getId() + "invoked sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail() throws  Exception{
        System.out.println(Thread.currentThread().getId() + "invoked sendEmail");
    }
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }
    public void get() {
        lock.lock();
//        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "invoked get");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            lock.unlock();
            lock.unlock();
        }
    }
    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "invoked set");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");

        t3.start();
        t4.start();
    }
}
