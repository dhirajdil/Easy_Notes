package com.in28minutes.rest.webservices.restfulwebservices;

import java.net.URI;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource{
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/user")
	public List<User> retriveAllUsers(){
		
		return service.findAll();
	}
	@GetMapping("/user/{id}")
	public User retriveUser(@PathVariable int id) {
		
		User user=service.findOne(id);
		if(user==null) {
			throw new UserNotFoundException(""+id);
		}
		
	return user;
	}
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser=service.save(user);
		URI location=
				ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		         return ResponseEntity.created(location).build();
		
	}
	
	
	}