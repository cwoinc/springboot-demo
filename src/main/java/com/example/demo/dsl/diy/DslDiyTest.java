package com.example.demo.dsl.diy;

import com.example.demo.dsl.diy.entity.Person;

/**
 * @author king
 * @version 2018-08-01 6:59 PM
 */
public class DslDiyTest {
    
    public static void main(String[] args) {
        Person person = PersonBuilder.person()
                .name("king")
                .age(18)
                .build();
        
    }
    
}
