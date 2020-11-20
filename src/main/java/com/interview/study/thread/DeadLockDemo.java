package com.interview.study.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author lwc
 * @date 2020/11/19 16:00
 * Description:
 * 死锁是指两个或者以上的进程在执行过程中,
 * 因争夺资源而造成的一种相互等待的现象,
 * 若无外力干涉那他们都将无法推进下去
 */
class HoldThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "持有锁" + lockA);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "持有锁" + lockB);
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "LockA";
        String lockB = "LockB";

        new Thread(new HoldThread(lockA, lockB), "A").start();
        new Thread(new HoldThread(lockB, lockA), "B").start();
    }
}
