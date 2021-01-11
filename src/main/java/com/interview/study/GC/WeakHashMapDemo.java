package com.interview.study.GC;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author liwenchang
 * @create 2020-12-09 22:09
 * WeakHashMap key为弱引用的hashmap 一旦进行垃圾回收 key指向null 就会被删除
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
//        myHashmap();
        myWeakHashmap();
    }

    private static void myWeakHashmap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "WeakHashMap";
        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map);
    }

    private static void myHashmap() {
        HashMap<Integer,String> map = new HashMap<>();
        Integer key =  new Integer(1);
        String value = "hashMap";
        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map);
    }
}
