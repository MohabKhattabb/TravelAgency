package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Hotel;
import com.example.demo.model.Response;
import com.example.demo.service.HotelService;
import com.example.demo.service.HotelServiceImpl;

@RestController
@RequestMapping("/HotelController")
public class HotelController {

    @Autowired
    HotelService hotelService = new HotelServiceImpl();

    @PostMapping("/add")
    public Response addHotel(@RequestBody Hotel hotel) {
        System.out.println("Adding hotel: " + hotel);
        boolean res = hotelService.addHotel(hotel);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Hotel already exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Hotel created successfully");
        return response;
    }

    @PutMapping("/update")
    public Response updateHotel(@RequestParam("id") int id, @RequestParam("newName") String name) {
        boolean res = hotelService.updateHotel(id, name);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Hotel doesn't exist to update.");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Hotel updated successfully.");
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public Response deleteHotel(@PathVariable("id") int id) {
        System.out.println("Deleting hotel with id: " + id);
        boolean res = hotelService.deleteHotel(id);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Hotel doesn't exist");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Hotel deleted successfully");
        return response;
    }

    @GetMapping("/get/{id}")
    public Response<Hotel> getHotel(@PathVariable("id") int id) {
        System.out.println("Fetching hotel with id: " + id);
        var hotel = hotelService.getHotel(id);
        Response<Hotel> response = new Response<>();
        if (hotel == null) {
            response.setStatus(false);
            response.setMessage("Hotel not found");
            response.object = null;
            return response;
        }

        response.setStatus(true);
        response.setMessage("Hotel retrieved successfully");
        response.object = hotel;
        return response;
    }

    @GetMapping("/get")
    public Hotel[] getAllHotels() {
        System.out.println("Fetching all hotels");
        return hotelService.getAllHotels();
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "elhotel 10 3la 10 ";
    }


}
