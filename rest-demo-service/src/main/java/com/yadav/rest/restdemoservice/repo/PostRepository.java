package com.yadav.rest.restdemoservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yadav.rest.restdemoservice.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
