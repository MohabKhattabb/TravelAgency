package com.example.demo.service;

import com.example.demo.model.Hotel;


public interface HotelService {

    public Boolean addHotel(Hotel hotel);

    public Boolean updateHotel(int id, String newName);

    public Boolean deleteHotel(int id);

    public Hotel getHotel(int id);

    public Hotel[] getAllHotels();

}
