package com.example.demo.service;

import com.example.demo.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    Notification createOrUpdateNotification(Notification notification);

    boolean deleteNotification(Long id);

    Optional<Notification> getNotificationById(Long id);

    List<Notification> getAllNotifications();

    List<Notification> getNotificationsByUserId(Long userId);

    void markAsRead(Long id);
}
