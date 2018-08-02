package com.example.demo.dsl.method.chain;

import com.example.demo.dsl.entity.Graph;

/**
 * @author king
 * @version 2018-07-31 8:58 AM
 */
public class GraphBuilder {
    
    private Graph graph;
    
    private GraphBuilder() {
        graph = new Graph();
    }
    
    //Start the graph DSL with this method.
    public static GraphBuilder graph() {
        return new GraphBuilder();
    }
    
    //Start the edge building with this method.
    public EdgeBuilder edge() {
        EdgeBuilder builder = new EdgeBuilder(this);
        
        getGraph().addEdge(builder.edge);
        
        return builder;
    }
    
    public Graph getGraph() {
        return graph;
    }
    
    public void printGraph() {
        Graph.printGraph(graph);
    }
}

