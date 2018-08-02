package com.example.demo.dsl.diy.lambda;

import java.util.function.Consumer;

/**
 * @author king
 * @version 2018-08-02 6:52 PM
 */
public class Look {
    
    public static void main(String[] args) {
        dfjh(i -> {
            System.out.println("66");
        });
    }
    
    public static void dfjh(Consumer<String> comparable) {
        comparable.accept(null);
        comparable.andThen(dddsgfjh(i -> {
            System.out.println("2");
        }));
        
    }
    
    public static Consumer<String> dddsgfjh(Consumer<String> comparable) {
        comparable.accept("");
        return comparable;
    }
    
}
