package com.example.demo.dsl.lambda;

import com.example.demo.dsl.entity.Edge;
import com.example.demo.dsl.entity.Vertex;

/**
 * Populates the Edge model.
 *
 * @author king
 * @version 2018-07-31 9:11 AM
 */
public class EdgeBuilder {
    private Edge e;
    
    public EdgeBuilder() {
        e = new Edge();
    }
    
    public Edge edge() {
        return e;
    }
    
    public void from(String lbl) {
        e.setFromVertex(new Vertex(lbl));
    }
    
    public void to(String lbl) {
        e.setToVertex(new Vertex(lbl));
    }
    
    public void weight(Double w) {
        e.setWeight(w);
    }
    
}
