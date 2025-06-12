package com.example.demo.service;

import com.example.demo.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event createOrUpdateEvent(Event event);
    boolean deleteEvent(int id);
    Optional<Event> getEventById(int id);
    List<Event> getAllEvents();
}
