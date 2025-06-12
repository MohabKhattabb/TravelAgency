package com.example.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.model.Notification;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FileService {
    private static final String FILE_PATH = "path_to_your_file.json";

    public void writeToJsonFile(List<Notification> notifications) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Write to a file in the file system
            mapper.writeValue(new File(FILE_PATH), notifications);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Notification> readFromJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Read from the file system
            return mapper.readValue(new File(FILE_PATH), mapper.getTypeFactory().constructCollectionType(List.class, Notification.class));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
