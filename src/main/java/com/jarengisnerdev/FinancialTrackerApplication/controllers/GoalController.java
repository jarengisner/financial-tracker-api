package com.jarengisnerdev.FinancialTrackerApplication.controllers;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.GoalService;
import com.jarengisnerdev.FinancialTrackerApplication.models.Goal;
import com.jarengisnerdev.FinancialTrackerApplication.utility.JwtUtil;
import com.jarengisnerdev.FinancialTrackerApplication.utility.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ResourceBundle;

@RestController
public class GoalController {

    @Autowired
    private GoalService goalService;

    @GetMapping("/goals/{id}")
    public ResponseEntity<Goal> getGoalByGoalId(@PathVariable Long id, @RequestHeader("Authorization") String token){
        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                Goal fetchedGoal = goalService.getGoalByGoalId(id);

                if(fetchedGoal == null){
                    return ResponseEntity.notFound().build();
                }else {
                    return ResponseEntity.ok(fetchedGoal);
                }

            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/goals/{trackerId}/all")
    public ResponseEntity<List<Goal>> getAllGoalsByTrackerId(@PathVariable Long trackerId, @RequestHeader("Authorization") String token){
        try{

            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                List<Goal> currentGoalList = goalService.getAllGoalsByTrackerId(trackerId);

                if(currentGoalList.isEmpty()){
                    return ResponseEntity.noContent().build();
                }else{
                    return ResponseEntity.ok(currentGoalList);
                }
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    };

    @PostMapping("/goals")
    public ResponseEntity<?> createNewGoal(@RequestBody Goal goal, @RequestHeader("Authorization") String token){
        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                Goal freshGoal = goalService.createNewGoal(goal);

                return new ResponseEntity<>(freshGoal, HttpStatus.CREATED);
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return new ResponseEntity<>("Failed to create a new goal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*
    Goal needs to be sent in the form of a complete goal, even though we will only pull the new message,
    just so that a new Goal object can be built out of the sent json
     */
    @PutMapping("/goal/edit/{id}")
    public ResponseEntity<?> updateGoal(@RequestBody Goal goal, @PathVariable Long id, @RequestHeader("Authorization") String token){
        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                Goal currentlyEditingGoal = goalService.getGoalByGoalId(id);

                if(currentlyEditingGoal == null){
                    return ResponseEntity.notFound().build();
                }else{
                    currentlyEditingGoal.setMessage(goal.getMessage());
                    Goal postEditingGoal = goalService.updateGoal(currentlyEditingGoal);
                    return new ResponseEntity<>(postEditingGoal, HttpStatus.CREATED);
                }
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return new ResponseEntity<>("Failed to update goal" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    };

    @DeleteMapping("/goal/delete/{id}")
    public ResponseEntity<?> deleteGoal(@PathVariable Long id, @RequestHeader("Authorization") String token){
        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                };

                Goal goalToDelete = goalService.getGoalByGoalId(id);

                if(goalToDelete == null){
                    return ResponseEntity.notFound().build();
                }else{
                    goalService.deleteGoal(id);

                    MessageResponse message = new MessageResponse("Goal Successfully Deleted");

                    return ResponseEntity.ok(message);
                }
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return new ResponseEntity<>("Failed to delete goal " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
