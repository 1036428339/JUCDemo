package com.interview.study.GC;

/**
 * @author liwenchang
 * @create 2020-12-09 21:02
 * 强引用
 * 死都不回收
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();//这样定义默认就是强引用
        Object obj2 = obj1;//obj2引用赋值
        obj1 = null;//置空
        System.gc();
        System.out.println(obj1);
        System.out.println(obj2);
    }
}
