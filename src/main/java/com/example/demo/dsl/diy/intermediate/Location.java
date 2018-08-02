package com.example.demo.dsl.diy.intermediate;

/**
 * @author king
 * @version 2018-08-01 9:18 PM
 */
public class Location {
    
    private String paris;
    
    private String hilton;
    
    private Location(String paris) {
        this.paris = paris;
    }
    
    public static Location city(String paris) {
        return new Location(paris);
    }
    
    public Location hotel(String hilton) {
        this.hilton = hilton;
        return this;
    }
    
    public String getParis() {
        return paris;
    }
    
    public void setParis(String paris) {
        this.paris = paris;
    }
    
    public String getHilton() {
        return hilton;
    }
    
    public void setHilton(String hilton) {
        this.hilton = hilton;
    }
}
