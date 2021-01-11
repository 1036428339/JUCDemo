package com.interview.study.GC;

/**
 * @author liwenchang
 * @create 2020-12-13 15:55
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowerror();
    }

    private static void stackOverflowerror() {
        stackOverflowerror();
    }
}
