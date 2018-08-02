package com.example.demo.dsl.lambda;

import com.example.demo.dsl.entity.Edge;
import com.example.demo.dsl.entity.Graph;

import java.util.function.Consumer;

/**
 * Populates the graph model.
 *
 * @author king
 * @version 2018-07-31 9:10 AM
 */
public class GraphBuilder {
    
    private Graph graph;
    
    private GraphBuilder() {
        graph = new Graph();
    }
    
    public static Graph graph(Consumer<GraphBuilder> consumer) {
        GraphBuilder builder = new GraphBuilder();
        consumer.accept(builder);
        return builder.graph;
    }
    
    /**
     * 注意这个方法参数，这是java 8支持的函数参数
     */
    public void edge(Consumer<EdgeBuilder> consumer) {
        EdgeBuilder builder = new EdgeBuilder();
        consumer.accept(builder);
        Edge edge = builder.edge();
        graph.addEdge(edge);
        graph.addVertice(edge.getFromVertex());
        graph.addVertice(edge.getToVertex());
    }
}
