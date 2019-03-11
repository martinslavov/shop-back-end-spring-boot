package com.shop.api.controller;

import com.shop.api.model.User;
import com.shop.api.model.UserDto;
import com.shop.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * Created by Martin Slavov on 01/08/2018.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	
	private static Logger logger = Logger.getLogger("UserController");

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @GetMapping(value = "/users/{id}")
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }
    
    @GetMapping(value = "/users/all")
    public List<User> listUserAll(){
        return userService.findAll();
    }

	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="/signup")
    public ResponseEntity<?> saveUser(@RequestBody UserDto user){
		  logger.info("Creat user");
		  logger.debug("Creat user - debug");
		  logger.error("Creat user - error");
		  // logger.fatal("Creat user - fatal");
		  // return userService.save(user);
    	try{
  		  return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
  		  // return new ResponseEntity<>(HttpStatus.CREATED);
  	  	}catch(Exception ex){
  		    String errorMessage;
  		    ex.printStackTrace();
  		    errorMessage = ex + " <== error";
  		    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_ACCEPTABLE);
  		}
    }

}
