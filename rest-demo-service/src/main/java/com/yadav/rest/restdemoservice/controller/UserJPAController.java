package com.yadav.rest.restdemoservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yadav.rest.restdemoservice.dao.UserDAO;
import com.yadav.rest.restdemoservice.model.Post;
import com.yadav.rest.restdemoservice.model.User;
import com.yadav.rest.restdemoservice.repo.PostRepository;
import com.yadav.rest.restdemoservice.repo.UserRepository;

@RestController
public class UserJPAController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retriveAllusers()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}/post")
	public List<Post> getUser(@PathVariable int id)
	{
		Optional<User> ust= userRepository.findById(id);
		if(!ust.isPresent())
		{
		throw new UserNotFoundException("id-"+id);
		}
		
		return ust.get().getPost();
		
	}
	
    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Object> userPost(@PathVariable int id,  @RequestBody Post post)
    {
    	Optional<User> ust= userRepository.findById(id);
		if(!ust.isPresent())
		{
		throw new UserNotFoundException("id-"+id);
		}
		
		post.setUser(ust.get());
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		    	.path("/{id}")
		        .buildAndExpand(post.getId()).toUri();
		    	return ResponseEntity.created(location).build();
    	
    }
    	
    
    
}
	
	

