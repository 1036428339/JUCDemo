package com.interview.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lwc
 * @date 2020/11/6 9:27
 * 哲学家吃饭问题
 * 5个线程争夺5把锁 同时获得左右两把锁时吃饭 吃完释放锁
 */

class table{
    List<ReentrantLock> forks = new ArrayList<>();
}
public class EatFood {
    public static void main(String[] args) {
        table table = new table();
        //造5把叉子
        for (int i = 0; i < 5; i++) {
            ReentrantLock reentrantLock = new ReentrantLock();
            table.forks.add(reentrantLock);
        }
        //造5个哲学家
        for (int i = 0; i < table.forks.size(); i++) {
            final int index = i;
            if(1 == i%2){
                new Thread(()->{
                    table.forks.get(index-1<0?table.forks.size()-1:index-1).lock();
                    table.forks.get(index).lock();
//                    System.out.println(Thread.currentThread().getName()+CountryEnum.forEach_CountryEnum(index).getName()+"吃饭");
                    System.out.println(Thread.currentThread().getName()+" food ");
                    table.forks.get(index-1<0?table.forks.size()-1:index-1).unlock();
                    table.forks.get(index).unlock();
                },String.valueOf(i)).start();
            }else {
                new Thread(()->{
                    table.forks.get(index).lock();
                    table.forks.get(index-1<0?table.forks.size()-1:index-1).lock();
//                    System.out.println(Thread.currentThread().getName()+CountryEnum.forEach_CountryEnum(index).getName()+"吃饭");
                    System.out.println(Thread.currentThread().getName()+" food ");
                    table.forks.get(index).unlock();
                    table.forks.get(index-1<0?table.forks.size()-1:index-1).unlock();
                },String.valueOf(i)).start();
            }
        }


    }
}
