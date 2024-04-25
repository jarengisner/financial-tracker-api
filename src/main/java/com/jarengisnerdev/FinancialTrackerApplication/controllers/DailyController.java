package com.jarengisnerdev.FinancialTrackerApplication.controllers;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.DailyService;
import com.jarengisnerdev.FinancialTrackerApplication.models.Daily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DailyController {

    @Autowired
    DailyService dailyService;

    @GetMapping("/daily/{id}")
    public ResponseEntity<Daily> getDailyById(@PathVariable Long id){
        Daily currentQueryDaily = dailyService.getDailyById(id);

        if(currentQueryDaily == null){
           return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(currentQueryDaily);
        }
    };

    @GetMapping("/daily/{trackerId}/all")
    public ResponseEntity<List<Daily>> getAllDailyByTrackerId(@PathVariable Long trackerId){
        List<Daily> currentQueryDailyList = dailyService.getAllDailyByTrackerId(trackerId);

        if(currentQueryDailyList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(currentQueryDailyList);
        }
    };

    //date format must follow YYYY-MM-DD format
    @PostMapping("/daily")
    public ResponseEntity<?> createNewDaily(@RequestBody Daily daily){
        try{
            Daily brandNewDaily = dailyService.createNewDaily(daily);

            return new ResponseEntity<>(brandNewDaily,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Failed to create new Daily" + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/daily/update/{id}")
    public ResponseEntity<?> updateDailyById(@PathVariable Long id, @RequestBody Daily daily){
        Daily currentDailyToEdit = dailyService.getDailyById(id);

        if(currentDailyToEdit == null){
            return ResponseEntity.notFound().build();
        }else {
            currentDailyToEdit.setSaved(daily.getSaved());
            currentDailyToEdit.setSavedNote(daily.getSavedNote());
            currentDailyToEdit.setNeeds(daily.getNeeds());
            currentDailyToEdit.setNeedsNote(daily.getNeedsNote());
            currentDailyToEdit.setWants(daily.getWants());
            currentDailyToEdit.setWantsNote(daily.getWantsNote());

            //Attempt to save the new record after updating, with error handling if needed
            try{
                Daily dailyAfterEditing = dailyService.updateDaily(currentDailyToEdit);
                return new ResponseEntity<>(dailyAfterEditing, HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>("Failed to update daily record: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    };


}
