package com.api.user.service.web.controller;

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

import com.api.user.service.web.entity.User;
import com.api.user.service.web.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser") //url: http://localhost:8081/user/addUser
	public ResponseEntity<User> addNewUser(@RequestBody User user){
		User usr = userService.saveUser(user);
		//return new ResponseEntity<User>(usr, HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(usr);
	}
	
	@GetMapping("/getUserById/{userId}") //url: http://localhost:8081/user/getUserById/{userId}
	public ResponseEntity<User> getUserById(@PathVariable String userId){
		User usr = userService.getUser(userId);
		return ResponseEntity.ok(usr);
	}
	
	@GetMapping("/getAllUsers") //url: http://localhost:8081/user/getAllUsers
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> allUsers = userService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}
	
	@PutMapping("/updateUserByUserId/{userId}")  //url: http://localhost:8081/user/updateUserByUserId/{userId}
	public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
	    User updatedUser = userService.updateUser(userId, user);
	    return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}

	
	@DeleteMapping("/deleteUserByUserId/{userId}") //url: http://localhost:8081/user/deleteUserByUserId/{userId}
	public void deleteUserById(@PathVariable String userId) {
		userService.deleteUserById(userId);
	}
}
