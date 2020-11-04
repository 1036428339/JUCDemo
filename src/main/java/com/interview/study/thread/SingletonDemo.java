package com.interview.study.thread;

/**
 * @author liwenchang
 * @create 2020-10-30 21:15
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+" 构造方法");
    }
    //DCL (double check lock 双端检索机制)
    public static SingletonDemo getInstance(){
        if (instance == null) {
            synchronized (SingletonDemo.class){
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        //单线程下没问题
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        //多线程下
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
