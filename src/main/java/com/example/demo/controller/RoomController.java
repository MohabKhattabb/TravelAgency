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

import com.example.demo.model.Response;
import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import com.example.demo.service.RoomServiceImpl;

@RestController
@RequestMapping("/RoomController")
public class RoomController {

    @Autowired
    RoomService roomService = new RoomServiceImpl();

    @PostMapping("/add")
    public Response addRoom(@RequestBody Room room) {
        boolean res = roomService.addRoom(room);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Room Already Exists");
            return response;
        }
        response.setStatus(true);
        response.setMessage("Room added successfully");
        return response;
    }

    @PutMapping("/update")
    public Response updateRoom(@RequestParam("id") int id, @RequestParam("newLocation") String location) {
        boolean res = roomService.updateRoom(id, location);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Room doesn't exist to update.");
            return response;
        }
        response.setStatus(true);
        response.setMessage("Room updated successfully.");
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public Response deleteRoom(@PathVariable("id") int id) {
        boolean res = roomService.deleteRoom(id);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Room doesn't exist.");
            return response;
        }
        response.setStatus(true);
        response.setMessage("Room deleted successfully.");
        return response;
    }

    @GetMapping("/get/{id}")
    public Response<Room> getRoom(@PathVariable("id") int id) {
        Room room = roomService.getRoom(id);
        Response<Room> response = new Response<>();
        if (room == null) {
            response.setStatus(false);
            response.setMessage("Room not found.");
            response.object = null;
            return response;
        }
        response.setStatus(true);
        response.setMessage("Room retrieved successfully.");
        response.object = room;
        return response;
    }

    @GetMapping("/get")
    public Room[] getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "elroom zy elfol";
    }

}
