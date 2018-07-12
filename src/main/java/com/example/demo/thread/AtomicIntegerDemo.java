package com.example.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author king
 * @version 2018-07-10 8:10 PM
 */
public class AtomicIntegerDemo {
    //创建AtomicInteger,用于自增操作
    
    static AtomicInteger i = new AtomicInteger();
    
    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i.incrementAndGet();
            }
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        //开启10条线程同时执行i的自增操作
        for (int k = 0; k < 10; k++) {
            ts[k] = new Thread(new AddThread());
        }
        //启动线程
        for (int k = 0; k < 10; k++) {
            ts[k].start();
        }
        
        for (int k = 0; k < 10; k++) {
            ts[k].join();
        }
        
        //输出结果:100000
        System.out.println(i);
    }
}