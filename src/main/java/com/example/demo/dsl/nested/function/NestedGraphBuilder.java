package com.example.demo.dsl.nested.function;

import com.example.demo.dsl.entity.Edge;
import com.example.demo.dsl.entity.Graph;

/**
 * @author king
 * @version 2018-07-31 9:01 AM
 */
public class NestedGraphBuilder {
    
    public static Graph graph(Edge... edges){
        Graph g = new Graph();
        for(Edge e : edges){
            g.addEdge(e);
            g.addVertice(e.getFromVertex());
            g.addVertice(e.getToVertex());
        }
        return g;
    }
    
}