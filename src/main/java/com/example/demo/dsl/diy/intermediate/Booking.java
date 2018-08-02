package com.example.demo.dsl.diy.intermediate;

/**
 * @author king
 * @version 2018-08-01 9:18 PM
 */
public class Booking {
    
    private Location location;
    
    private Flight flight;
    
    public Booking(Location location) {
        this.location = location;
    }
    
    public void add(Flight flight) {
        this.flight = flight;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public Flight getFlight() {
        return flight;
    }
    
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
