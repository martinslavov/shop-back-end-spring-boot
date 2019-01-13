package com.shop.api.service;

import java.util.List;

import com.shop.api.model.User;
import com.shop.api.model.UserDto;

/**
 * Created by Martin Slavov on 01/08/2018.
 */

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);
    User findById(Long id);
}
