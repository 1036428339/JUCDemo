package com.interview.study.GC;

import java.lang.ref.SoftReference;

/**
 * @author liwenchang
 * @create 2020-12-09 21:33
 * 软引用
 * 内存够用就保留 不够用就回收
 */
public class SoftReferenceDemo {
    public static void softRef_Memory_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    /**
     * 故意设置大对象小内存 查看软引用对象的情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();
        try {
            byte[] bytes = new byte[10*1024*1024];
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args) {
//        softRef_Memory_Enough();
        softRef_Memory_NotEnough();
    }
}
