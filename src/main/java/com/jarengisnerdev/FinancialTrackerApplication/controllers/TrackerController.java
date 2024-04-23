package com.jarengisnerdev.FinancialTrackerApplication.controllers;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.TrackerService;
import com.jarengisnerdev.FinancialTrackerApplication.models.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrackerController {
    @Autowired
    private TrackerService trackerService;

    //This endpoint will simply find one tracker by the tracker's id
    @GetMapping("/trackers/{id}")
    public ResponseEntity<Tracker> getTrackerBySpecifiedId(@PathVariable Long id) {
        Tracker currentQueryTracker = trackerService.getTrackerById(id);

        if(currentQueryTracker == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(currentQueryTracker);
        }
    }

    @GetMapping("/trackers/{userid}/all")
    public ResponseEntity<List<Tracker>> getAllUserTrackers(@PathVariable Long userid){
        List<Tracker> userTrackers = trackerService.getAllUsersTrackers(userid);

        if(userTrackers.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(userTrackers);
        }
    };


}
