package com.api.user.service.web.feignClients.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.api.user.service.web.entity.Hotel;

@FeignClient("HOTEL-SERVICE")
public interface HotelService {

	@GetMapping("/hotel/getHotelById/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
