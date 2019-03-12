package com.shop.api.model;

/**
 * The Class AuthToken.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
public class AuthToken {

    /** The token. */
    private String token;

    /**
     * Instantiates a new auth token.
     */
    public AuthToken(){

    }

    /**
     * Instantiates a new auth token.
     *
     * @param token the token
     */
    public AuthToken(String token){
        this.token = token;
    }

    /**
     * Gets the token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     *
     * @param token the new token
     */
    public void setToken(String token) {
        this.token = token;
    }

}
