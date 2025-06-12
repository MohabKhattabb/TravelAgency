package com.example.demo.controller;

import com.example.demo.model.Notification;
import com.example.demo.service.NotificationService;
import com.example.demo.service.SmsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SmsServiceImp smsService;

    @PostMapping("/add")
    public ResponseEntity<String> addNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createOrUpdateNotification(notification);
        return ResponseEntity.ok("Notification added successfully with ID: " + createdNotification.getId());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateNotificationReadStatus(@PathVariable Long id, @RequestParam("status") boolean status) {
        Optional<Notification> existingNotification = notificationService.getNotificationById(id);
        if (existingNotification.isPresent()) {
            notificationService.markAsRead(id);
            return ResponseEntity.ok("Notification status updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Notification with ID " + id + " not found.");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/get")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id) {
        boolean result = notificationService.deleteNotification(id);
        return result ? ResponseEntity.ok("Notification deleted successfully.")
                : ResponseEntity.badRequest().body("Failed to delete notification. Notification with ID " + id + " not found.");
    }

    @PostMapping("/sendSMS")
    public ResponseEntity<String> sendSMS(@RequestBody Map<String, String> smsDetails) {
        String phoneNumber = smsDetails.get("phoneNumber");
        String message = smsDetails.get("message");

        if (phoneNumber == null || message == null) {
            return ResponseEntity.badRequest().body("Phone number and message are required.");
        }

        smsService.sendSMS(phoneNumber, message);
        return ResponseEntity.ok("SMS sent successfully to: " + phoneNumber);
    }
}
