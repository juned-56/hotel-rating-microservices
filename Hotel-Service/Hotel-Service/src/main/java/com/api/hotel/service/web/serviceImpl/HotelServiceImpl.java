package com.api.hotel.service.web.serviceImpl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.hotel.service.web.entity.Hotel;
import com.api.hotel.service.web.exception.ResourceNotFoundException;
import com.api.hotel.service.web.repository.HotelRepository;
import com.api.hotel.service.web.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	
	@Override
	public Hotel saveHotel(Hotel hotel) {
		String randomHotelId = UUID.randomUUID().toString();
		hotel.setId(randomHotelId);
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel updateHotel(String id, Hotel hotel) {
		if(!hotelRepository.existsById(id)) {
			throw new ResourceNotFoundException("Hotel not found with id: " + id);
		}
		hotel.setId(id);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(String id) {
		return hotelRepository.findById(id).orElseThrow(()-> new com.api.hotel.service.web.exception.ResourceNotFoundException("Hotel with given id is not found!! "+id));
	}

	@Override
	public void deleteHotelById(String id) {
		hotelRepository.deleteById(id);
	}

}