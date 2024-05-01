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



    public Tracker(String tracker_name, int user_id, boolean year, boolean month, BigDecimal savings_goal){
        this.tracker_name = tracker_name;

        //userId is assigned on creation, linking the tables, there will be no way to change this
        this.user_id = user_id;

        this.year = year;

        this.month = month;

        this.savings_goal = savings_goal;
    }



    //Tracker name setters and getters
    public void setTrackerName(String tracker_name) {
        this.tracker_name = tracker_name;
    };

    public String getTrackerName() {
        return tracker_name;
    }


    //tracker id is auto completed by the system, we do not need to set
    public int getTrackerId() {
        return tracker_id;
    };


    //UserId setters and getters
    public int getUserId() {
        return user_id;
    };

    public void setUserId(int user_id){
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
    public BigDecimal getSavingsGoal(){
        return this.savings_goal;
    }

    public void setSavingsGoal(BigDecimal savings_goal) {
        this.savings_goal = savings_goal;
    };


}
