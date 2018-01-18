package com.yadav.rest.restdemoservice.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yadav.rest.restdemoservice.model.User;

@Component
public class UserDAO {

	public static List<User> users = new ArrayList<>();
	
	public static int id = 3;
	static
	{
		users.add(new User(1,"Ajay",new Date()));
		users.add(new User(2,"Ansh",new Date()));
		users.add(new User(3,"Aavya",new Date()));
	}
	
	public List<User> findAll()
	{
		return users;
	}
	
	public User saveUser(User usr)
	{
		if(usr.getId()==null)
		{
			usr.setId(id++);
		}
		users.add(usr);
		return usr;
	}
	
	public User findOne(int id)
	{
		for(User user:users)
		{
			if(user.getId().equals(id))
				return user;
		}
		return null;
	}
	
	public User deleteById(int id)
	{
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext())
		{
			User user = iterator.next();
			if(user.getId()==id)
			{
			iterator.remove();
			return user;
			}
		}
		return null;
	}
}
