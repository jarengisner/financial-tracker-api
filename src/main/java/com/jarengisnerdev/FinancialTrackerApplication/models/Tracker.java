package com.jarengisnerdev.FinancialTrackerApplication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trackers")
public class Tracker {
    //Tracker id is the PK of the Tracker class, used to identify them
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tracker_id;

    //tracker name will just hold the name of the tracker
    @JsonProperty("tracker_name")
    @Column(nullable = false)
    private String tracker_name;

    //user_id will hold the id of the user that created the tracker, is used as FK
    @JsonProperty("user_id")
    @Column(nullable = false)
    private int user_id;

    @JsonProperty("year")
    @Column()
    private boolean year;

    @JsonProperty("month")
    @Column()
    private boolean month;

    @JsonProperty("savings_goal")
    @Column()
    private BigDecimal savings_goal;

    @JsonProperty("wants_goal")
    @Column()
    private BigDecimal wants_goal;

    @JsonProperty("needs_goal")
    @Column
    private BigDecimal needs_goal;



    public Tracker(String tracker_name, int user_id, boolean year, boolean month, BigDecimal savings_goal, BigDecimal wants_goal, BigDecimal needs_goal){
        this.tracker_name = tracker_name;

        //userId is assigned on creation, linking the tables, there will be no way to change this
        this.user_id = user_id;

        this.year = year;

        this.month = month;

        this.savings_goal = savings_goal;

        this.wants_goal = wants_goal;

        this.needs_goal = needs_goal;
    }



    //Tracker name setters and getters
    //tracker names were originally plain camelCase
    public void setTracker_name(String tracker_name) {
        this.tracker_name = tracker_name;
    };

    public String getTracker_name() {
        return tracker_name;
    }


    //tracker id is auto completed by the system, we do not need to set
    public int getTracker_id() {
        return tracker_id;
    };


    //UserId setters and getters
    public int getUser_id() {
        return user_id;
    };

    public void setUser_id(int user_id){
        this.user_id = user_id;
    };


    public boolean isYear(){
        return this.year;
    };

    public void setYear(boolean year){
        this.year = year;
    }
    public boolean isMonth(){
        return this.month;
    }

    public void setMonth(boolean month){
        this.month = month;
    };

    //savings goal getters and setters
    public BigDecimal getSavings_goal(){
        return this.savings_goal;
    }

    public void setSavings_goal(BigDecimal savings_goal) {
        this.savings_goal = savings_goal;
    };

    public BigDecimal getWants_goal(){
        return this.wants_goal;
    };

    public void setWants_goal(BigDecimal wants_goal){this.wants_goal = wants_goal;}


    public BigDecimal getNeeds_goal(){return this.needs_goal;}
    public void setNeeds_goal(BigDecimal needs_goal){this.needs_goal = needs_goal;}


}
