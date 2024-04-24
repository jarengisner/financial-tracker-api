package com.jarengisnerdev.FinancialTrackerApplication.controllers;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.GoalService;
import com.jarengisnerdev.FinancialTrackerApplication.models.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoalController {

    @Autowired
    private GoalService goalService;

    @GetMapping("/goals/{id}")
    public ResponseEntity<Goal> getGoalByGoalId(@PathVariable Long id){
            Goal fetchedGoal = goalService.getGoalByGoalId(id);

            if(fetchedGoal == null){
                return ResponseEntity.notFound().build();
            }else {
                return ResponseEntity.ok(fetchedGoal);
            }
    }

    @GetMapping("/goals/{trackerId}/all")
    public ResponseEntity<List<Goal>> getAllGoalsByTrackerId(@PathVariable Long trackerId){
        List<Goal> currentGoalList = goalService.getAllGoalsByTrackerId(trackerId);

        if(currentGoalList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(currentGoalList);
        }
    };

    @PostMapping("/goals")
    public ResponseEntity<?> createNewGoal(@RequestBody Goal goal){
        try{
            Goal freshGoal = goalService.createNewGoal(goal);

            return new ResponseEntity<>(freshGoal, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Failed to create a new goal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
