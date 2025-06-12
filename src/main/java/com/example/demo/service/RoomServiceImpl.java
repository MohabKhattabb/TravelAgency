package com.example.demo.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.model.Room;
import static com.example.demo.util.Common.rooms;

@Service
public class RoomServiceImpl implements RoomService {

    @Override
    public Boolean addRoom(Room room) {
        if (rooms.get(room.getId()) != null) {
            return false;
        }
        rooms.put(room.getId(), room);
        return true;
    }

    @Override
    public Boolean deleteRoom(int id) {
        if (rooms.get(id) == null) {
            return false;
        }
        rooms.remove(id);
        return true;
    }

    @Override
    public Room getRoom(int id) {
        return rooms.get(id);
    }

    @Override
    public Room[] getAllRooms() {
        Set<Integer> ids = rooms.keySet();
        Room[] roomArray = new Room[ids.size()];
        int i = 0;
        for (Integer id : ids) {
            roomArray[i] = rooms.get(id);
            i++;
        }
        return roomArray;
    }

    @Override
    public Boolean updateRoom(int id, String newLocation) {
        Room room = rooms.get(id);
        if (room == null) {
            return false;
        }
        room.setLocation(newLocation);
        return true;
    }

    
}
