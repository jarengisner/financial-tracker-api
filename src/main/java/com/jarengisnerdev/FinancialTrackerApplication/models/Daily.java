package com.jarengisnerdev.FinancialTrackerApplication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@NoArgsConstructor
public class Daily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int daily_id;

    @JsonProperty("saved")
    @Column
    private BigDecimal saved;

    @JsonProperty("savednote")
    @Column(length = 250)
    private String savednote;

    @JsonProperty("needs")
    @Column()
    private BigDecimal needs;

    @JsonProperty("needsnote")
    @Column(length = 250)
    private String needsnote;

    @JsonProperty("wants")
    @Column
    private BigDecimal wants;

    @JsonProperty("wantsnote")
    @Column(length = 250)
    private String wantsnote;

    @JsonProperty("entrydate")
    @Column(nullable = false)
    //needs to be sent as YYYY-MM-DD
    private Date entrydate;

    @JsonProperty("tracker_id")
    @Column(nullable = false)
    private Long tracker_id;
}
