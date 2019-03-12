package com.shop.api.controller;

import com.shop.api.config.TokenProvider;
import com.shop.api.model.AuthToken;
import com.shop.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * The Class AuthenticationController.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/token")
public class AuthenticationController {

    /** The authentication manager. */
    @Autowired
    private AuthenticationManager authenticationManager;

    /** The jwt token util. */
    @Autowired
    private TokenProvider jwtTokenUtil;

    /**
     * Generate token for login.
     *
     * @param loginUser
     * @return the response entity
     * @throws AuthenticationException the authentication exception
     */
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }
}