package com.jarengisnerdev.FinancialTrackerApplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "trackers")
public class Tracker {
    //Tracker id is the PK of the Tracker class, used to identify them
    private int tracker_id;

    //tracker name will just hold the name of the tracker
    private String tracker_name;

    //user_id will hold the id of the user that created the tracker, is used as FK
    private int user_id;


    public Tracker(){}

    public Tracker(String tracker_name, int user_id){
        this.tracker_name = tracker_name;

        //userId is assigned on creation, linking the tables, there will be no way to change this
        this.user_id = user_id;
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
}
