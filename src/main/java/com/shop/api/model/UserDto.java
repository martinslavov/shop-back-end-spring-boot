package com.shop.api.model;

/**
 * The Class UserDto.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
public class UserDto {

    /** The username. */
    private String username;
    
    /** The password. */
    private String password;
    
    /** The age. */
    private int age;
    
    /** The salary. */
    private int salary;

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age.
     *
     * @param age the new age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the salary.
     *
     * @return the salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Sets the salary.
     *
     * @param salary the new salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }
}