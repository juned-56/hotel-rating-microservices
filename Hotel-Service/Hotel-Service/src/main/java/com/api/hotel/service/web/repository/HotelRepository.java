package com.api.hotel.service.web.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.hotel.service.web.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
