package com.api.hotel.service.web.service;
import java.util.List;
import com.api.hotel.service.web.entity.Hotel;

public interface HotelService {

	Hotel saveHotel(Hotel hotel);
	Hotel updateHotel(String id, Hotel hotel);
	List<Hotel> getAllHotels();
	Hotel getHotelById(String id);
	void deleteHotelById(String id);
}
