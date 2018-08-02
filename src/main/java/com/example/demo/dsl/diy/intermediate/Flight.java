package com.example.demo.dsl.diy.intermediate;

/**
 * @author king
 * @version 2018-08-01 9:18 PM
 */
public class Flight {
    
    private String united;
    
    private String flight;
    
    private Flight(String united) {
        this.united = united;
    }
    
    public static Flight airline(String united) {
        return new Flight(united);
    }
    
    public Flight flight(String flight) {
        this.flight = flight;
        return this;
    }
    
    public String getUnited() {
        return united;
    }
    
    public void setUnited(String united) {
        this.united = united;
    }
    
    public String getFlight() {
        return flight;
    }
    
    public void setFlight(String flight) {
        this.flight = flight;
    }
}




