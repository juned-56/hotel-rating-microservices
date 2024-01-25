package com.api.user.service.web.service.impl;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.api.user.service.web.entity.Hotel;
import com.api.user.service.web.entity.Rating;
import com.api.user.service.web.entity.User;
import com.api.user.service.web.exception.ResourceNotFoundException;
import com.api.user.service.web.feignClients.service.HotelService;
import com.api.user.service.web.repository.UserRepository;
import com.api.user.service.web.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	Rating rating = new Rating();
	
	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		
		User usr = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User id is not found on the server!!! "+userId));
		//http://localhost:8081/user/getUserById/2c950828-cf06-496a-a24e-4222c6241e3a
		//"http://localhost:8083/rating/user/"+usr.getUserId(), Rating[].class
		Rating[] ratingsOfUser =  restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+usr.getUserId(), Rating[].class);
		logger.info("[]",ratingsOfUser);
		List<Rating> ratings =  Arrays.stream(ratingsOfUser).toList();
		List<Rating> ratingList = ratings.stream().map(rating -> {
			//http://localhost:8082/hotel/getHotelById/0df58d90-ff05-4127-a7c6-5a2cb37d4b65
			//"http://localhost:8082/hotel/getHotelById/"+rating.getHotelId(), Hotel.class
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/getHotelById/"+rating.getHotelId(), Hotel.class);
			//Hotel hotel = forEntity.getBody();
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			//logger.info("response status code: {}", forEntity.getStatusCode());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		usr.setRatings(ratingList);
		return usr; 
	}

	@Override
	public void deleteUserById(String userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public User updateUser(String userId, User user) {
		//return userRepository.save(userId);
		if (!userRepository.existsById(userId)) {
	        throw new ResourceNotFoundException("User not found with id: " + userId);
	    }
	    // Set the userId for the user object
	    user.setUserId(userId);
	    return userRepository.save(user);
	}

}
