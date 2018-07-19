package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    
    @Test
    public void contextLoads() {
        String aa="你\u200B\u200B\u200B\u200B\u200C\u200B\u200D\u200B\u200B\u200B\u200B\u200B\u200C\u200D\u200C\u200C\u200B\u200B\u200B\u200B\u200C\u200D\uFEFF\u200B\u200B\u200B\u200B\u200B\u200C\u200D\uFEFF\u200B\u200B\u200B\u200B\u200B\u200C\u200D\uFEFF\uFEFF\u200B\u200B\u200B\u200B\u200B\u200D\u200B\u200B\u200B\u200B\u200B\u200B\u200C\u200C\u200C\uFEFF\u200B\u200B\u200B\u200B\u200C\u200D\uFEFF\uFEFF\u200B\u200B\u200B\u200B\u200C\uFEFF\u200B\u200D\u200B\u200B\u200B\u200B\u200C\u200D\uFEFF\u200B\u200B\u200B\u200B\u200B\u200C\u200D\u200C\u200B\u200B\u200B\u200B\u200B\u200B\u200D\u200B\u200C好";
        System.out.println(aa);
    }
    
    public static void main(String[] args) {
        boolean flag = ClassUtils.isPresent("com.wantdo.springboot20180626.Tt", null);
        System.out.println(flag);
        
        String[] strings = new String[1];
        System.out.println(strings);
        try {
            Class<?> class1 = ClassUtils.forName("java.lang.String[]", null);
            System.out.println(class1);
            Class<?> class2 = ClassUtils.forName(strings.getClass().getName(), null);
            System.out.println(class2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Integer[] integers = new Integer[1];
        System.out.println(integers);
        
        int[] ints = new int[1];
        System.out.println(ints);
        System.out.println(ints.getClass().getName());
        
        Date date = new Date();
    }
    
    class Tt {
    
    }
    
}
