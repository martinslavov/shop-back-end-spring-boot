package com.shop.api.config;

/**
 * The Class Constants.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01 
 */
public class Constants {

    /** The Constant ACCESS_TOKEN_VALIDITY_SECONDS. */
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    
    /** The Constant SIGNING_KEY. */
    public static final String SIGNING_KEY = "devglan123r";
    
    /** The Constant TOKEN_PREFIX. */
    public static final String TOKEN_PREFIX = "Bearer ";
    
    /** The Constant HEADER_STRING. */
    public static final String HEADER_STRING = "Authorization";
    
    /** The Constant AUTHORITIES_KEY. */
    public static final String AUTHORITIES_KEY = "scopes";
}
