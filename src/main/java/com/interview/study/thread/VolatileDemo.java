package com.interview.study.thread;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liwenchang
 * @create 2020-10-29 22:16
 */
class myData{
    volatile int numble = 0;
    public void addTo60(){
        this.numble = 60;
    }
    //numble前面已经加了volatile关键字
    public   void addPlusPlus(){
        numble++;
    }
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtonic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1 验证volatile的可见性
 *   1.1 假如 int numble = 0 ; numble变量之前根本没有添加volatile关键字修饰 没有可见性
 *   1.2 添加了volatile 可以解决可见性问题
 * 2 验证volatile不能保证原子性
 *   2.1 原子性指的是什么意思？不可分割 完整性 既某个线程正在做某个业务时不能被加塞或分割需要整体完整
 *   要么同时成功，要么同时失败
 *   2.2 不保证原子性案例演示
 *   2.3 如何解决原子性
 *      * synchronized
 *      * AtomicInteger
 */
public class VolatileDemo {
    public static void main(String[] args) {
        myData myData = new myData();

        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtonic();
                }
            },String.valueOf(i)).start();
        }

        //需要等待上面线程全部完成计算后 再用main线程查看最终的结果是多少
        while (Thread.activeCount() >2){
            //活动线程>2 没算完
            Thread.yield();//礼让其他线程 这里不工作
        }
        System.out.println(Thread.currentThread().getName()+" numble "+ myData.numble);
        System.out.println(Thread.currentThread().getName()+" numble "+ myData.atomicInteger);
    }
    //volatile 可以保证可见性 及时通知其他线程，主物理内存中的变量已经被修改
    private static void seeOKVolatile() {
        myData myData = new myData(); //线程要操作资源类

        new Thread( ()->{
            System.out.println(Thread.currentThread().getName()+" come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+" updated numble value:"+myData.numble);
        },"AAA").start();

        //第二个线程就是我们的main线程
        while (myData.numble == 0){
            //一直循环直到numble 不为0为止
        }
        System.out.println(Thread.currentThread().getName()+" mission is over main get numble "+myData.numble);
    }
}
