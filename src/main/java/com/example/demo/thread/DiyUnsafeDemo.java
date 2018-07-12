package com.example.demo.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author king
 * @version 2018-07-12 7:03 PM
 */
public class DiyUnsafeDemo {
    
    private static ParentDemo parentDemo;
    
    public static void main(String[] args) throws Exception {
        Unsafe unsafe = null;
        try {
            unsafe = Unsafe.getUnsafe();
        } catch (Exception e) {
        
        }
        System.out.println(unsafe);
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        unsafe = (Unsafe) theUnsafe.get(null);
        System.out.println(unsafe);
        parentDemo = (ParentDemo) unsafe.allocateInstance(ParentDemo.class);
        parentDemo.setName("66");
        System.out.println(parentDemo.getName());
        
        //T1 t1 = new T1();
        //t1.start();
        
        Field name = ParentDemo.class.getDeclaredField("name");
        Field number = ParentDemo.class.getDeclaredField("number");
        long nameOffset = unsafe.objectFieldOffset(name);
        long numberOffset = unsafe.objectFieldOffset(number);
        boolean flag = unsafe.compareAndSwapObject(parentDemo, nameOffset, "8", "77");
        System.out.println(parentDemo.getName() + "\t" + flag);
        
        while (true) {
            int oldValue = parentDemo.getNumber();
            unsafe.compareAndSwapInt(parentDemo, numberOffset, oldValue, 9);
            System.out.println(System.nanoTime());
        }
    }
    
    static class T1 implements Runnable {
        
        @Override
        public void run() {
            for (; ; ) {
                System.out.println(parentDemo.getNumber());
                int i = parentDemo.getNumber() + 1;
                parentDemo.setNumber(i);
            }
        }
    }
    
}

class ParentDemo {
    private String name;
    private int number;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
}
