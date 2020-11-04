package com.interview.study.thread;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author liwenchang
 * @create 2020-11-01 9:44
 * 集合类不安全问题
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        Map<String,String> map = Collections.synchronizedMap(new HashMap<>());//new ConcurrentHashMap<>();//new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
    private static void setNosafe() {
        //        Set<String> set = Collections.synchronizedSet(new HashSet<>());
//        Set<String> set = new CopyOnWriteArraySet<>();
        HashSet<String> set = new HashSet<>();
        set.add("a");

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void listNosafe() {
        //        ArrayList<String> list = new ArrayList<>();
//        Vector<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        //并发线程较多时 由于arrayList add方法没有加synchronized
        // 可能会出现java.util.ConcurrentModificationException
        /**
         * 1 故障现象
         *      java.util.ConcurrentModificationException
         * 2 导致原因
         *      并发争抢修改导致，一个线程正在写，另一个线程争抢导致数据不一致
         * 3 解决方案
         *      3.1 new Vector<>();
         *      3.2 Collections.synchronizedList(new ArrayList<>());
         *      3.3 new CopyOnWriteArrayList<>();
         * 4 优化建议（同样的错误不犯第二次）
         */
    }
}
