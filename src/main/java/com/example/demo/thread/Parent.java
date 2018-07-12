package com.example.demo.thread;

/**
 * @author king
 * @version 2018-07-10 8:19 PM
 */
// 父线程
public class Parent extends Thread {
    @Override
    public void run() {
        System.out.println("parent start");
        Child child = new Child();
        child.start();
        try {
            // 在哪个线程中调用该方法，则挂起哪个线程，直到被调用的子线程运行完成后才继续执行
            child.join();
            //Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("parent end");
    }
    
    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.start();
    }
}

class Child extends Thread {
    @Override
    public void run() {
        System.out.println("child start");
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("start sleep " + i);
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("child end");
    }
}