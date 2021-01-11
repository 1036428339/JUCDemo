package com.interview.study.GC;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author liwenchang
 * @create 2020-12-09 22:52
 * 虚引用 phantomReference的get方法总是返回null 形同虚设
 * 回收前放入引用队列中 提供回收前通知或后置处理机制
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        o1=null;
        System.gc();
        System.out.println("===========");
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
