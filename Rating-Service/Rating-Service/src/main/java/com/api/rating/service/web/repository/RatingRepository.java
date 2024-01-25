package com.api.rating.service.web.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.rating.service.web.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{

	//customer finder method
	List<Rating> findByUserId(String userId);
	//hotel finder method
	List<Rating> findByHotelId(String hotelId);
}
