package com.example.demo.service;

import com.example.demo.model.Room;

public interface RoomService {

    public Boolean addRoom(Room room);

    public Boolean updateRoom(int id, String newLocation);

    public Boolean deleteRoom(int id);

    public Room getRoom(int id);

    public Room[] getAllRooms();
}
