package com.victor.stockalarms.dto;

public class UserDTO {

    private String name;
    private String password;
    private String email;

    public UserDTO() {
    }

    public UserDTO(final String name, final String password, final String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}