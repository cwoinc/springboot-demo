package com.example.demo.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

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
        
        //while (true) {
        //    int oldValue = parentDemo.getNumber();
        //    unsafe.compareAndSwapInt(parentDemo, numberOffset, oldValue, 9);
        //    System.out.println(System.nanoTime());
        //}
        
        /*
        没有持有引用时强行修改值
         */
        ParentDemo p1 = new ParentDemo();
        ParentDemo p2 = new ParentDemo();
        unsafe.putInt(p1, sizeOf(p1) + unsafe.objectFieldOffset(number), 42);
        System.out.println(p2.getNumber());
        
        //long intClassAddress = normalize(unsafe.getInt(new Integer(0), 4L));
        //long strClassAddress = normalize(unsafe.getInt("", 4L));
        //unsafe.putAddress(intClassAddress + 36, strClassAddress);
        //
        //String dfga = (String) (Object) (new Integer(666));
        //System.out.println(dfga);
    
    }
    
    private static long normalize(int value) {
        if (value >= 0) {
            return value;
        }
        return (~0L >>> 32) & value;
    }
    
    public static long sizeOf(Object o) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe u = (Unsafe) theUnsafe.get(null);
        HashSet<Field> fields = new HashSet<Field>();
        Class c = o.getClass();
        while (c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }
        
        // get offset
        long maxSize = 0;
        for (Field f : fields) {
            long offset = u.objectFieldOffset(f);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }
        
        return ((maxSize / 8) + 1) * 8;   // padding
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
