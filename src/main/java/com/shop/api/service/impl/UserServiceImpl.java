package com.shop.api.service.impl;

import com.shop.api.model.User;
import com.shop.api.model.UserDto;
import com.shop.api.repository.UserRepository;
import com.shop.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The Class UserServiceImpl.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The bcrypt encoder. */
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	/**
	 * Gets the authority.
	 *
	 * @param the user
	 * @return the authority
	 */
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.UserService#findAll()
	 */
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.UserService#delete(long)
	 */
	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.UserService#findOne(java.lang.String)
	 */
	@Override
	public User findOne(String username) {
		return userRepository.findByUsername(username);
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.UserService#findById(java.lang.Long)
	 */
	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.shop.api.service.UserService#save(com.shop.api.model.UserDto)
	 */
	@Override
    public User save(UserDto user) {
	    User newUser = new User();
	    newUser.setUsername(user.getUsername());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAge(user.getAge());
		newUser.setSalary(user.getSalary());
//		newUser.setRoles(Set<Role> role);
        return userRepository.save(newUser);
    }
}
