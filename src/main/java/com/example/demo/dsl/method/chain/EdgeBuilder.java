package com.example.demo.dsl.method.chain;

import com.example.demo.dsl.entity.Edge;
import com.example.demo.dsl.entity.Vertex;

/**
 * @author king
 * @version 2018-07-31 8:59 AM
 */
public class EdgeBuilder {
    
    Edge edge;
    
    //Keep a back reference to the graph Builder.
    private GraphBuilder builder;
    
    public EdgeBuilder(GraphBuilder gBuilder) {
        this.builder = gBuilder;
        edge = new Edge();
    }
    
    public EdgeBuilder from(String lbl) {
        Vertex v = new Vertex(lbl);
        edge.setFromVertex(v);
        builder.getGraph().addVertice(v);
        return this;
    }
    
    public EdgeBuilder to(String lbl) {
        Vertex v = new Vertex(lbl);
        edge.setToVertex(v);
        builder.getGraph().addVertice(v);
        return this;
    }
    
    public GraphBuilder weight(Double d) {
        edge.setWeight(d);
        return builder;
    }
    
}
