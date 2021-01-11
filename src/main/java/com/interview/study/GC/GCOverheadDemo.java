package com.interview.study.GC;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liwenchang
 * @create 2020-12-13 16:15
 * JVM参数设置
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * GC回收时间过长会抛出OutOfMemroyError
 * 过长的定义是超过98%的时间用来做GC并且回收了不到2%的堆内存
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }
}
