package com.example.demo.service;

import com.example.demo.model.Hotel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    private static final String JSON_FILE_PATH = "C:\\Users\\MOHAB\\Desktop\\SpringCodeExample\\src\\main\\resources\\Hotel.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Hotel> loadHotels() {
        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Hotel>>() {});
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    private void saveHotels(List<Hotel> hotels) {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), hotels);
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

    @Override
    public Boolean addHotel(Hotel hotel) {
        List<Hotel> hotels = loadHotels();
        if (hotels.stream().anyMatch(h -> h.getId() == hotel.getId())) {
            return false;
        }
        hotels.add(hotel);
        saveHotels(hotels);
        return true;
    }

    @Override
    public Boolean deleteHotel(int id) {
        List<Hotel> hotels = loadHotels();
        boolean removed = hotels.removeIf(h -> h.getId() == id);
        if (removed) {
            saveHotels(hotels);
        }
        return removed;
    }

    @Override
    public Hotel getHotel(int id) {
        List<Hotel> hotels = loadHotels();
        return hotels.stream().filter(h -> h.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Hotel[] getAllHotels() {
        List<Hotel> hotels = loadHotels();
        return hotels.toArray(new Hotel[0]);
    }

    @Override
    public Boolean updateHotel(int id, String newName) {
        List<Hotel> hotels = loadHotels();
        for (Hotel hotel : hotels) {
            if (hotel.getId() == id) {
                hotel.setName(newName);
                saveHotels(hotels);
                return true;
            }
        }
        return false;
    }
}
