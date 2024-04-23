package com.jarengisnerdev.FinancialTrackerApplication.models;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public User(){}


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    };

    public String getUsername(){
        return this.username;
    };

    public void setPassword(String password){
        this.password = password;
    };

    public Long getID(){
        return this.user_id;
    };
}
