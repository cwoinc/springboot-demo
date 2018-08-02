package com.example.demo.dsl.diy.intermediate;

/**
 * @author king
 * @version 2018-08-01 9:17 PM
 */

public class Vacation {
    
    private String startDate;
    
    private String endDate;
    
    private Booking booking;
    
    private Vacation(String startDate) {
        this.startDate = startDate;
    }
    
    public static Vacation from(String startDate) {
        return new Vacation(startDate);
    }
    
    public Vacation to(String endDate) {
        this.endDate = endDate;
        return this;
    }
    
    public Booking book(Location hotel) {
        return new Booking(hotel);
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public Booking getBooking() {
        return booking;
    }
    
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
