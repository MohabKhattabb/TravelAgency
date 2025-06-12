package com.example.demo.service;

import com.example.demo.model.Notification;
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
public class NotificationServiceImpl implements NotificationService {

    private static final String FILE_PATH = "C:\\Users\\MOHAB\\Downloads\\jsUpdatedTravel_NoDB (1)\\jjUpdatedTravel_NoDB\\src\\main\\resources\\notifications.json";
    private final HashMap<Long, Notification> notifications = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NotificationServiceImpl() {
        loadNotifications();
    }

    private void loadNotifications() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                List<Notification> loadedNotifications = objectMapper.readValue(file, new TypeReference<List<Notification>>() {});
                loadedNotifications.forEach(notification -> notifications.put(notification.getId(), notification));
            }
        } catch (IOException e) {
            System.err.println("Error loading notifications: " + e.getMessage());
        }
    }

    private void saveNotifications() {
        try {
            objectMapper.writeValue(new File(FILE_PATH), notifications.values());
        } catch (IOException e) {
            System.err.println("Error saving notifications: " + e.getMessage());
        }
    }

    @Override
    public Notification createOrUpdateNotification(Notification notification) {
        if (notification.getId() == null) {
            throw new IllegalArgumentException("Notification ID cannot be null.");
        }
        notifications.put(notification.getId(), notification);
        saveNotifications();
        return notification;
    }

    @Override
    public boolean deleteNotification(Long id) {
        boolean isRemoved = notifications.remove(id) != null;
        if (isRemoved) {
            saveNotifications();
        }
        return isRemoved;
    }

    @Override
    public Optional<Notification> getNotificationById(Long id) {
        return Optional.ofNullable(notifications.get(id));
    }

    @Override
    public List<Notification> getAllNotifications() {
        return new ArrayList<>(notifications.values());
    }

    @Override
    public List<Notification> getNotificationsByUserId(Long userId) {
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification : notifications.values()) {
            if (notification.getUser() != null && Long.valueOf(notification.getUser().getId()).equals(userId)) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }

    @Override
    public void markAsRead(Long id) {
        Notification notification = notifications.get(id);
        if (notification != null) {
            notification.setReadStatus(true);
            saveNotifications();
        }
    }
}
