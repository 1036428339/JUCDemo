package com.interview.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *  @author liwenchang
 *  @create 2020-11-05 21:06
 *  ArrayBlockingQueue 基于数组结构的有界阻塞队列，此队列按FIFO原则对元素进行排序
 *  LinkedBlockingQueue 一个基于链表结构的阻塞队列，此队列FIFO排序元素，吞吐量通常要高于ArrayBlockingQueue
 *  SyschronousQueue 一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常需要高
 *
 *  阻塞队列有没有好的一面
 *
 *  不得不阻塞，你如何管理
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName()+" put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName()+" put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" take 1");
                blockingQueue.take();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" take 2");
                blockingQueue.take();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" take 3");
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }

    private static void blockingTime() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
//        System.out.println(blockingQueue.offer("x", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
//        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
    }

    private static void waitBlocking() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("=====队列被阻塞了=======");
//        blockingQueue.put("x");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
//        blockingQueue.take();
    }

    private static void returnBlooen() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    private static void throwException() {
        //        List list = new ArrayList();
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("x"));

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
    }
}
