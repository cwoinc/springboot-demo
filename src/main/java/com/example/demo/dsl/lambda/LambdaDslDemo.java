package com.example.demo.dsl.lambda;

import com.example.demo.dsl.entity.Graph;

/**
 * @author king
 * @version 2018-07-31 9:12 AM
 */
public class LambdaDslDemo {
    public static void main(String[] args) {
        Graph g1 = GraphBuilder.graph(g -> {
            g.edge(e -> {
                e.from("a");
                e.to("b");
                e.weight(12.4);
            });
            
            g.edge(e -> {
                e.from("c");
                e.to("d");
                e.weight(13.4);
            });
        });
        
        Graph.printGraph(g1);
        
    }
}
