package com.api.rating.service.web.service;

import java.util.List;

import com.api.rating.service.web.entity.Rating;

public interface RatingService {

	Rating createRating(Rating rating);
	List<Rating> allRatings();
	List<Rating> getRatingByUserId(String userId);
	List<Rating> getRatingByHotelId(String hotelId);
}
