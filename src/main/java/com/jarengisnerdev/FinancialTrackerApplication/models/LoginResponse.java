package com.jarengisnerdev.FinancialTrackerApplication.models;

public class LoginResponse {
    private String username;

    private Long id;

    private String token;

    public LoginResponse(String username, Long id, String token){
        this.username = username;
        this.id = id;
        this.token = token;
    };


    public void setUsername(String username) {
        this.username = username;
    };

    public void setId(Long id){
        this.id = id;
    };

    public void setToken(String token){
        this.token = token;
    };

    public String getUsername(){
        return this.username;
    };

    public Long getId(){
        return this.id;
    };

    public String getToken(){
        return this.token;
    };


}
