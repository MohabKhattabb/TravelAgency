package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SmsServiceImp  {


    public void sendSMS(String phoneNumber, String message) {
        // Simulate sending an SMS (integrate with an SMS gateway here)
        System.out.println("Sending SMS to: " + phoneNumber);
        System.out.println("Message: " + message);
    }
}
