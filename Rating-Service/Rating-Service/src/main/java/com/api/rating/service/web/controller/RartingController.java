package com.api.rating.service.web.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.rating.service.web.entity.Rating;
import com.api.rating.service.web.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RartingController {

	@Autowired
	private RatingService ratingService;
	
	
	@PostMapping("/addRating") //url: http://localhost:8083/rating/addRating
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		Rating rting = ratingService.createRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(rting);
	}
	
	@GetMapping("/getAllRatings") //url: http://localhost:8083/rating/getAllRatings
	public ResponseEntity<List<Rating>> getAllRatings(){
		List<Rating> ratingList = ratingService.allRatings();
		return ResponseEntity.ok(ratingList);
	}
	
	@GetMapping("/user/{userId}")  //url: http://localhost:8083/rating/user/{userId}
	public ResponseEntity<List<Rating>> getAllRatingsUserId(@PathVariable String userId){
		List<Rating> ratingListByUserId = ratingService.getRatingByUserId(userId);
		return ResponseEntity.ok(ratingListByUserId);
	}
	
	@GetMapping("/hotel/{hotelId}") //url: http://localhost:8083/rating/hotel/{hotelId}
	public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable String hotelId){
		List<Rating> ratingListByHotelId = ratingService.getRatingByHotelId(hotelId);
		return ResponseEntity.ok(ratingListByHotelId);
	}
}
