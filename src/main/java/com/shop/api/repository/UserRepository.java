package com.shop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.api.model.User;

/**
 * The Interface UserRepository.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find by username.
     *
     * @param the username
     * @return the user
     */
    User findByUsername(String username);
}
