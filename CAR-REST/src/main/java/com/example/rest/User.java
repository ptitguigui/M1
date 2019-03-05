package com.example.rest;


public class User {
	private String username;
    private String password;
    /**
     * @return the firstName
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setUsername(String firstName) {
        this.username = firstName;
    }
    /**
     * @return the lastName
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setPassword(String lastName) {
        this.password = lastName;
    }
}
