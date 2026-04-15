package com.pvtool.hr.dto;

public class LoginResponse {

    private String name;
    private String role;
    private String token;

    public LoginResponse(String name, String role, String token) {
        this.name = name;
        this.role = role;
        this.token = token;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}