package com.jarengisnerdev.FinancialTrackerApplication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goal_id;

    @JsonProperty("message")
    @Column(nullable = false)
    private String message;

    @JsonProperty("tracker_id")
    @Column(nullable = false)
    private Long tracker_id;

    public Goal(String message, Long tracker_id){
        this.message = message;
        this.tracker_id = tracker_id;
    };

    public Long getGoal_id() {
        return goal_id;
    };

    public String getMessage() {
        return message;
    };

    public void setMessage(String message) {
        this.message = message;
    };

    public Long getTracker_id() {
        return tracker_id;
    };

    public void setTracker_id(Long trackerId){
        this.tracker_id = trackerId;
    }
}
