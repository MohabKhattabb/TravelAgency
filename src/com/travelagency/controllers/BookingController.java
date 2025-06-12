package com.travelagency.controllers;

import java.util.ArrayList;
import java.util.List;

public class BookingController {

    private List<String> hotelDatabase = new ArrayList<>(); // Mock database for hotels
    private List<String> eventDatabase = new ArrayList<>(); // Mock database for events

    public BookingController() {
        // Populate mock databases
        hotelDatabase.add("Hotel A - Location: City Center, Price: $100");
        hotelDatabase.add("Hotel B - Location: Beachside, Price: $150");
        eventDatabase.add("Event A - Date: 2024-12-25, Location: Concert Hall");
        eventDatabase.add("Event B - Date: 2024-12-31, Location: Open Park");
    }

    public void searchHotels(String criteria) {
        System.out.println("Searching for hotels matching criteria: " + criteria);
        // Display search results to the user
        for (String hotel : hotelDatabase) {
            if (hotel.toLowerCase().contains(criteria.toLowerCase())) {
                System.out.println(hotel);
            }
        }
        // Enable user to filter and sort search results (mock example)
        System.out.println("Filter and sorting options can be added here.");
    }

    public void bookHotel(String details) {
        System.out.println("Booking hotel with details: " + details);
        // Perform necessary validations and booking process
        if (details == null || details.isEmpty()) {
            System.out.println("Error: Booking details are invalid.");
            return;
        }
        System.out.println("Hotel booked successfully: " + details);
        // Send booking confirmation email (mock example)
        System.out.println("Booking confirmation email sent to the user.");
    }

    public void searchEvents(String criteria) {
        System.out.println("Searching for events matching criteria: " + criteria);
        // Display search results to the user
        for (String event : eventDatabase) {
            if (event.toLowerCase().contains(criteria.toLowerCase())) {
                System.out.println(event);
            }
        }
        // Enable user to filter and sort search results (mock example)
        System.out.println("Filter and sorting options can be added here.");
    }

    public void bookEvent(String details) {
        System.out.println("Booking event with details: " + details);
        // Perform necessary validations and booking process
        if (details == null || details.isEmpty()) {
            System.out.println("Error: Booking details are invalid.");
            return;
        }
        System.out.println("Event booked successfully: " + details);
        // Send booking confirmation email (mock example)
        System.out.println("Booking confirmation email sent to the user.");
    }
}
