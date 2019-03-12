package com.shop.api.service;

import java.util.List;

import com.shop.api.model.User;
import com.shop.api.model.UserDto;

/**
 * The Interface UserService.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
public interface UserService {

    /**
     * Save.
     *
     * @param the user
     * @return the user
     */
    User save(UserDto user);
    
    /**
     * Find all.
     *
     * @return the list
     */
    List<User> findAll();
    
    /**
     * Delete.
     *
     * @param the id
     */
    void delete(long id);
    
    /**
     * Find one.
     *
     * @param the username
     * @return the user
     */
    User findOne(String username);
    
    /**
     * Find by id.
     *
     * @param the id
     * @return the user
     */
    User findById(Long id);
}
