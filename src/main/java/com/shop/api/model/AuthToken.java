package com.shop.api.model;

/**
 * Created by Martin Slavov on 01/08/2018.
 */

public class AuthToken {

    private String token;

    public AuthToken(){

    }

    public AuthToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
