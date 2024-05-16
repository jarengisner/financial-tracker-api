package com.jarengisnerdev.FinancialTrackerApplication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationRequest {

    @JsonProperty("token")
    private String token;

    public ValidationRequest(String token){
        this.token = token;
    };

    public void setToken(String token){
        this.token = token;
    };

    public String getToken(){
        return this.token;
    };
}
