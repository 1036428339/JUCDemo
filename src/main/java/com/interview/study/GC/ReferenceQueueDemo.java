package com.interview.study.GC;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author liwenchang
 * @create 2020-12-09 22:43
 * 引用队列
 * 虚引用 一定要与ReferenceQueue一起使用
 * 虚引用对象在被垃圾回收之前会被ReferenceQueue保存一下
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        o1=null;
        System.gc();
        System.out.println("============");
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }

}
