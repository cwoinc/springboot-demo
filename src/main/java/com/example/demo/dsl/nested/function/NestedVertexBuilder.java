package com.example.demo.dsl.nested.function;

import com.example.demo.dsl.entity.Vertex;

/**
 * Populates the Vertex model.
 *
 * @author king
 * @version 2018-07-31 9:02 AM
 */
public class NestedVertexBuilder {
    public static Vertex from(String lbl) {
        return new Vertex(lbl);
    }
    
    public static Vertex to(String lbl) {
        return new Vertex(lbl);
    }
}
