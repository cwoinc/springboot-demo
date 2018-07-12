package com.example.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class StreamDemo {

    private static List<Integer> list1 = new ArrayList<>();
    private static List<Integer> list2 = new ArrayList<>();
    private static List<Integer> list3 = new ArrayList<>();
    private static List<Integer> list4 = new ArrayList<>();
    private static Lock lock = new ReentrantLock();
    private static Lock lock1 = new ReentrantLock();

    public static void main(String[] args) {
        int maxLen = 100000;
        IntStream.range(0, maxLen).forEach(list1::add);

        IntStream.range(0, maxLen).parallel().forEach(list2::add);

        IntStream.range(0, maxLen).forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            } finally {
                lock.unlock();
            }
        });

        IntStream.range(0, maxLen).parallel().forEach(i -> {
            lock1.lock();
            try {
                list4.add(i);
            } finally {
                lock1.unlock();
            }
        });

        System.out.println("串行执行的大小：" + list1.size());
        System.out.println("并行执行的大小：" + list2.size());
        System.out.println("加锁串行执行的大小：" + list3.size());
        System.out.println("加锁并行执行的大小：" + list4.size());
    }

}
