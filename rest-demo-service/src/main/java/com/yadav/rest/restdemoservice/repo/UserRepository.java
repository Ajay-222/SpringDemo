package com.yadav.rest.restdemoservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yadav.rest.restdemoservice.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
