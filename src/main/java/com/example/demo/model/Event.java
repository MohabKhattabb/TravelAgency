package com.example.demo.model;

public class Event {
    private int id;
    private String name;
    private boolean ticketAvailable; // Changed from 'location' to 'ticketAvailable'
    private String date; // The date of the event remains unchanged

    // Default Constructor
    public Event() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTicketAvailable() {
        return ticketAvailable;
    }

    public void setTicketAvailable(boolean ticketAvailable) {
        this.ticketAvailable = ticketAvailable;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // toString method to represent the object in a clear, readable format
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ticketAvailable=" + ticketAvailable +
                ", date='" + date + '\'' +
                '}';
    }
}
