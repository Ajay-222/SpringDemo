package com.yadav.rest.restdemoservice.controller;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yadav.rest.restdemoservice.dao.UserDAO;
import com.yadav.rest.restdemoservice.model.User;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	
	@GetMapping("/users")
	public List<User> retriveAllusers()
	{
		return userDAO.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> getUser(@PathVariable int id)
	{
		User ust= userDAO.findOne(id);
		if(ust==null)
		{
		throw new UserNotFoundException("id-"+id);
		}
		
		Resource<User> resource = new Resource<User>(ust);
		try
		{
		
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllusers());
		resource.add(linkTo.withRel("all-users"));
		}
		catch(Exception ce)
		{
			ce.printStackTrace();
		}
		return resource;
	}
	
    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user)
    {
    	URI location = ServletUriComponentsBuilder.fromCurrentRequest()
    	.path("/{id}")
        .buildAndExpand(userDAO.saveUser(user).getId()).toUri();
    	
    	return ResponseEntity.created(location).build();
    	//return ResponseEntity.ok(userDAO.saveUser(user));
    }
    	
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
    	User user = userDAO.deleteById(id);
    	if(user == null)
    	{
    		throw new UserNotFoundException("id->"+id);
    	}
    	
    	
    }
    
}
	
	

