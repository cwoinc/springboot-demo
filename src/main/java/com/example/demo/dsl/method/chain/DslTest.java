package com.example.demo.dsl.method.chain;

/**
 * @author king
 * @version 2018-07-31 8:45 AM
 */
public class DslTest {
    
    public static void main(String[] args) {
        GraphBuilder.graph()
                .edge()
                .from("a")
                .to("b")
                .weight(12.3)
                .edge()
                .from("b")
                .to("c")
                .weight(10.5);
        
    }
    
}
