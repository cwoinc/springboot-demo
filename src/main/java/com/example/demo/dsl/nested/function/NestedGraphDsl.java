package com.example.demo.dsl.nested.function;

import com.example.demo.dsl.entity.Graph;

import static com.example.demo.dsl.nested.function.NestedEdgeBuilder.edge;
import static com.example.demo.dsl.nested.function.NestedEdgeBuilder.weight;
import static com.example.demo.dsl.nested.function.NestedGraphBuilder.graph;
import static com.example.demo.dsl.nested.function.NestedVertexBuilder.from;
import static com.example.demo.dsl.nested.function.NestedVertexBuilder.to;

/**
 * @author king
 * @version 2018-07-31 9:03 AM
 */
public class NestedGraphDsl {
    
    public static void main(String[] args) {
        Graph.printGraph(
                graph(
                        edge(from("a"), to("b"), weight(23.4)),
                        edge(from("b"), to("c"), weight(56.7)),
                        edge(from("d"), to("e"), weight(10.4)),
                        edge(from("e"), to("a"), weight(45.9))
                )
        );
        
    }
}
