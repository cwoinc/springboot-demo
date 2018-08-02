package com.example.demo.dsl.nested.function;

import com.example.demo.dsl.entity.Edge;
import com.example.demo.dsl.entity.Vertex;

/**
 * Populates the Edge model.
 *
 * @author king
 * @version 2018-07-31 9:02 AM
 */
public class NestedEdgeBuilder {
    
    public static Edge edge(Vertex from, Vertex to, Double weight) {
        return new Edge(from, to, weight);
    }
    
    public static Double weight(Double value) {
        return value;
    }
    
}
