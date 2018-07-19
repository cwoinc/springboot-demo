package com.example.demo.thread.package1;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

/**
 * @author king
 * @version 2018-07-13 3:06 PM
 */
public class DiyTest {
    
    public static void main(String[] args) throws Exception {
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);
        
        A a = (A) unsafe.allocateInstance(A.class);
        
        Field numberField = A.class.getDeclaredField("number");
        long numberOffset = unsafe.objectFieldOffset(numberField);
        unsafe.putOrderedInt(a, numberOffset, 8);
        System.out.println(a.getNumber());
        
        A a1 = new A();
        A a2 = new A();
        unsafe.putInt(a1, sizeOf(a1) + numberOffset, 8);
        System.out.println(a2.getNumber());
        
        
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
    
    private static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

class A {
    private int number;
    
    public A() {
        System.out.println("ghjjh");
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
}
