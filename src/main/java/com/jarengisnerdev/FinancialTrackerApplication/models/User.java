package com.jarengisnerdev.FinancialTrackerApplication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @JsonProperty("username")
    @Column(nullable = false, unique = true)
    private String username;

    @JsonProperty("password")
    @Column(nullable = false)
    private String password;

    public User(){}


    public User(String username, String password){
        this.username = username;
        setPassword(password);
    }

    public void setUsername(String username){
        this.username = username;
    };

    public String getUsername(){
        return this.username;
    };

    public String getPassword(){
        return this.password;
    };

    public void setPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    };

    //Made to be able to change password, with hashed pass upon creation of JSON object
    public void hashedPasswordUpdate(String hashedPassword){
        this.password = hashedPassword;
    };

    public Long getID(){
        return this.user_id;
    };

    public boolean verifyPassword(String rawPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, this.password);
    };
}
