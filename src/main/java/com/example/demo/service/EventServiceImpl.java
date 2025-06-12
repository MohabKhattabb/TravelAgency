package com.example.demo.service;

import com.example.demo.model.Event;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private static final String JSON_FILE_PATH = "C:\\Users\\MOHAB\\Desktop\\SpringCodeExample\\src\\main\\resources\\Event.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HashMap<Integer, Event> events = new HashMap<>();

    public EventServiceImpl() {
        loadEvents();
    }

    private void loadEvents() {
        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                List<Event> loadedEvents = objectMapper.readValue(file, new TypeReference<List<Event>>() {});
                loadedEvents.forEach(event -> events.put(event.getId(), event));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveEvents() {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), new ArrayList<>(events.values()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Event createOrUpdateEvent(Event event) {
        events.put(event.getId(), event);
        saveEvents();
        return event;
    }

    @Override
    public boolean deleteEvent(int id) {
        if (events.containsKey(id)) {
            events.remove(id);
            saveEvents();
            return true;
        }
        return false;
    }

    @Override
    public Optional<Event> getEventById(int id) {
        return Optional.ofNullable(events.get(id));
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(events.values());
    }
}
