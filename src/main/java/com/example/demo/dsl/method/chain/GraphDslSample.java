package com.example.demo.dsl.method.chain;

/**
 * @author king
 * @version 2018-07-31 9:00 AM
 */
public class GraphDslSample {
    
    public static void main(String[] args) {
        
        GraphBuilder.graph()
                .edge()
                .from("a")
                .to("b")
                .weight(40.0)
                .edge()
                .from("b")
                .to("c")
                .weight(20.0)
                .edge()
                .from("d")
                .to("e")
                .weight(50.5)
                .printGraph();
        
        GraphBuilder.graph()
                .edge()
                .from("w")
                .to("y")
                .weight(23.0)
                .edge()
                .from("d")
                .to("e")
                .weight(34.5)
                .edge()
                .from("e")
                .to("y")
                .weight(50.5)
                .printGraph();
        
    }
}
