package com.interview.study.GC;

import java.lang.ref.WeakReference;

/**
 * @author liwenchang
 * @create 2020-12-09 21:53
 * 弱引用
 * 一进行垃圾回收 弱引用对象指向null 就会被回收
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
