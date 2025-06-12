package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.model.Response;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/add")
    public ResponseEntity<Response<Event>> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createOrUpdateEvent(event);
        return ResponseEntity.ok(new Response<>(true, "Event created successfully", createdEvent));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response<Event>> updateEvent(@PathVariable int id, @RequestBody Event event) {
        event.setId(id);
        Event updatedEvent = eventService.createOrUpdateEvent(event);
        return ResponseEntity.ok(new Response<>(true, "Event updated successfully", updatedEvent));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<String>> deleteEvent(@PathVariable int id) {
        boolean isDeleted = eventService.deleteEvent(id);
        if (!isDeleted) {
            return ResponseEntity.badRequest().body(new Response<>(false, "No event found with ID " + id, "Deletion failed"));
        }
        return ResponseEntity.ok(new Response<>(true, "Event deleted successfully", "Event with ID " + id + " deleted"));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response<Event>> getEventById(@PathVariable int id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(value -> ResponseEntity.ok(new Response<>(true, "Event found", value)))
                .orElseGet(() -> ResponseEntity.badRequest().body(new Response<>(false, "No event found with ID " + id, null)));
    }

    @GetMapping("/all")
    public ResponseEntity<Response<List<Event>>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(new Response<>(true, "All events retrieved", events));
    }
}
