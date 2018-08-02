package com.example.demo.dsl.diy;

import com.example.demo.dsl.diy.entity.Person;

/**
 * @author king
 * @version 2018-08-01 8:18 PM
 */
public class PersonBuilder {
    
    private final Person person;
    
    private PersonBuilder() {
        person = new Person();
    }
    
    public static PersonBuilder person() {
        return new PersonBuilder();
    }
    
    public PersonBuilder name(String name) {
        person.setName(name);
        return this;
    }
    
    public PersonBuilder age(int age) {
        person.setAge(age);
        return this;
    }
    
    public Person build() {
        return person;
    }
    
}
