package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class UserDaoService{
	private List<User> users=new ArrayList<User>();
	
	private static int userCount=3;
	
	{
		users.add(new User(1,"dhiraj",new Date()));
		users.add(new User(2,"manish",new Date()));
		users.add(new User(3,"bihar",new Date()));
	}
	public List<User> findAll(){
		return users;
	}
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	}