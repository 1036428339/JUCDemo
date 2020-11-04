package com.interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author liwenchang
 * @create 2020-10-31 22:37
 */
public class ABADemo {//ABA问题的解决 AtomicStampedReference 带时间戳的原子引用
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {
        //ABA问题演示
//        new Thread(()->{
//            atomicReference.compareAndSet(100,101);
//            atomicReference.compareAndSet(101,100);
//        },"t1").start();
//
//        new Thread(()->{
//            //等待上面的线程完成ABA操作
//            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
//            System.out.println(atomicReference.compareAndSet(100, 666)+ atomicReference.get().toString());
//        },"t2").start();

        //ABA问题解决
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"第1次版本号：" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"第2次版本号：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"第3次版本号：" + atomicStampedReference.getStamp());
        },"t3").start();

        //等待上面的线程完成ABA操作
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"第1次版本号：" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100, 666, stamp, stamp + 1));
            System.out.println(Thread.currentThread().getName()+"第2次版本号：" + atomicStampedReference.getStamp());
        },"t4").start();
    }
}
