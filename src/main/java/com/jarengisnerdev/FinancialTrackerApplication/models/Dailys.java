package com.jarengisnerdev.FinancialTrackerApplication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@NoArgsConstructor
public class Dailys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int daily_id;

    @JsonProperty("saved")
    @Column
    private BigDecimal saved;

    @JsonProperty("saved_note")
    @Column(length = 250)
    private String saved_note;

    @JsonProperty("needs")
    @Column()
    private BigDecimal needs;

    @JsonProperty("needs_note")
    @Column(length = 250)
    private String needs_note;

    @JsonProperty("wants")
    @Column
    private BigDecimal wants;

    @JsonProperty("wants_note")
    @Column(length = 250)
    private String wants_note;

    @JsonProperty("entry_date")
    @Column(nullable = false)
    //needs to be sent as YYYY-MM-DD
    private Date entry_date;

    @JsonProperty("tracker_id")
    @Column(nullable = false)
    private Long tracker_id;

    public Dailys(BigDecimal saved, String saved_note, BigDecimal needs, String needs_note, BigDecimal wants, String wants_note, Date entry_date, Long tracker_id){
        this.saved = saved;
        this.saved_note = saved_note;
        this.needs = needs;
        this.needs_note = needs_note;
        this.wants = wants;
        this.wants_note = wants_note;
        this.entry_date = entry_date;
        this.tracker_id = tracker_id;
    };


    public int getDailyId(){
        return this.daily_id;
    }

    public BigDecimal getSaved() {
        return this.saved;
    };

    public void setSaved(BigDecimal saved){
        this.saved = saved;
    };

    public String getSaved_note(){
        return this.saved_note;
    };

    public void setSaved_note(String saved_note){
        this.saved_note = saved_note;
    };

    public BigDecimal getNeeds(){
        return this.needs;
    };

    public void setNeeds(BigDecimal needs){
        this.needs = needs;
    };

    public String getNeeds_note(){
        return this.needs_note;
    };

    public void setNeeds_note(String needs_note){
        this.needs_note = needs_note;
    };

    public BigDecimal getWants() {
        return this.wants;
    };

    public void setWants(BigDecimal wants){
        this.wants = wants;
    };

    public String getWants_note(){
        return this.wants_note;
    };


    public void setWants_note(String wants_note){
        this.wants_note = wants_note;
    };

    public Date getEntry_date(){
        return this.entry_date;
    };

    public void setEntry_date(Date entry_date){
        this.entry_date = entry_date;
    };

    public Long getTracker_id(){
        return this.tracker_id;
    };

    public void setTracker_id(Long tracker_id){
        this.tracker_id = tracker_id;
    };
}
