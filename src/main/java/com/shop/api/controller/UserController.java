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
 * The Class UserController.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger("UserController");

    /** The user service. */
    @Autowired
    private UserService userService;

    /**
     * Find all users.
     *
     * @return the list users
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    /**
     * Find the user by id
     *
     * @param id the id
     * @return the one
     */
    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @GetMapping(value = "/users/{id}")
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }
    
    /**
     * Find all users.
     *
     * @return the list
     */
    @GetMapping(value = "/users/all")
    public List<User> listUserAll(){
        return userService.findAll();
    }

	/**
	 * Save user.
	 *
	 * @param user
	 * @return the response entity
	 */
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="/signup")
    public ResponseEntity<?> saveUser(@RequestBody UserDto user){
		  logger.info("Creat user");
		  logger.debug("Creat user - debug");
    	try{
  		  return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
  	  	}catch(Exception ex){
  		    String errorMessage;
  		    ex.printStackTrace();
  		    errorMessage = ex + " <== error";
  		    logger.error("Creat user - error");
  		    logger.fatal("Creat user - fatal");
  		    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_ACCEPTABLE);
  		}
    }

}
