package com.example.demo.dsl.diy.intermediate;

/**
 * @author king
 * @version 2018-08-01 9:16 PM
 */
public class Test {
    
    public static void main(String[] args) {
        Vacation vacation = Vacation.from("10/09/2007").to("10/17/2007");
        Booking booking = vacation.book(Location.city("Paris").hotel("Hilton"));
        booking.add(Flight.airline("united").flight("UA-6886"));
    }
    
}
