package com.jarengisnerdev.FinancialTrackerApplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "trackers")
public class Tracker {
    //Tracker id is the PK of the Tracker class, used to identify them
    private int tracker_id;

    //tracker name will just hold the name of the tracker
    private String tracker_name;

    //user_id will hold the id of the user that created the tracker, is used as FK
    private int user_id;

    private boolean year;

    private boolean month;

    private BigDecimal savings_goal;


    public Tracker(){}

    public Tracker(String tracker_name, int user_id, boolean year, boolean month, BigDecimal savings_goal){
        this.tracker_name = tracker_name;

        //userId is assigned on creation, linking the tables, there will be no way to change this
        this.user_id = user_id;

        this.year = year;

        this.month = month;

        this.savings_goal = savings_goal;
    }

    public void setTrackerName(String tracker_name) {
        this.tracker_name = tracker_name;
    };

    public String getTrackerName() {
        return tracker_name;
    }

    public int getTrackerId() {
        return tracker_id;
    };

    public int getUserId() {
        return user_id;
    };

    public void toggleMonthOrYear(){
        if(!this.year){
            this.year = true;
            this.month = false;
        }else{
            this.year = false;
            this.month = true;
        }
    }

    public boolean getYearly(){
        return this.year;
    }

    public boolean getMonthly(){
        return this.month;
    }

    public BigDecimal getSavingsGoal(){
        return this.savings_goal;
    }

    public void setSavingsGoal(BigDecimal savings_goal) {
        this.savings_goal = savings_goal;
    };


}
