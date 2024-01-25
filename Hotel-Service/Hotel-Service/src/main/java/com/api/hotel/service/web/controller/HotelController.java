package com.api.hotel.service.web.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.hotel.service.web.entity.Hotel;
import com.api.hotel.service.web.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/addHotel") //url http://localhost:8082/hotel/addHotel
	public ResponseEntity<Hotel> addNewHotel(@RequestBody Hotel hotel){
		Hotel hotl = hotelService.saveHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotl);
	}
	
	@GetMapping("/getHotelById/{id}")  //url http://localhost:8082/hotel/getHotelById/{id}
	public ResponseEntity<Hotel> getHotelById(@PathVariable String id){
		Hotel hotl = hotelService.getHotelById(id);
		return ResponseEntity.ok(hotl);
	}
	
	@GetMapping("/getAllHotels")  //url http://localhost:8082/hotel/getAllHotels
	public ResponseEntity<List<Hotel>> getAllHotels(){
		List<Hotel> allHotels = hotelService.getAllHotels();
		return ResponseEntity.ok(allHotels);
	}
	
	@PutMapping("/updateHotelById/{id}")  //url http://localhost:8082/hotel/updateHotelById/{id}
	public ResponseEntity<Hotel> updateHotelById(@PathVariable String id, @RequestBody Hotel hotel){
		Hotel updateHotel = hotelService.updateHotel(id, hotel);
		return ResponseEntity.status(HttpStatus.OK).body(updateHotel);
	}
	
	@DeleteMapping("/deleteHotelById/{id}")  //url http://localhost:8082/hotel/deleteHotelById/{id}
	public void deleteHotelById(@PathVariable String id) {
		hotelService.deleteHotelById(id);
	}
}
