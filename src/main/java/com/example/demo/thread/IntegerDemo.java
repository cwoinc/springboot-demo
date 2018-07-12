package com.example.demo.thread;

/**
 * @author king
 * @version 2018-07-10 8:10 PM
 */
public class IntegerDemo {
    //创建AtomicInteger,用于自增操作
    
    static int i = 0;
    
    public static class AddThread implements Runnable {
        @Override
        public synchronized void run() {
            for (int k = 0; k < 10000; k++) {
                i++;
            }
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        AddThread addThread = new AddThread();
        //开启10条线程同时执行i的自增操作
        for (int k = 0; k < 10; k++) {
            ts[k] = new Thread(addThread);
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