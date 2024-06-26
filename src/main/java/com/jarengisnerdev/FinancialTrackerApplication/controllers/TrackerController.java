package com.jarengisnerdev.FinancialTrackerApplication.controllers;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.TrackerService;
import com.jarengisnerdev.FinancialTrackerApplication.models.Tracker;
import com.jarengisnerdev.FinancialTrackerApplication.utility.JwtUtil;
import com.jarengisnerdev.FinancialTrackerApplication.utility.MessageResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
public class TrackerController {
    @Autowired
    private TrackerService trackerService;

    //This endpoint will simply find one tracker by the tracker's id
    @GetMapping("/trackers/{id}")
    public ResponseEntity<Tracker> getTrackerBySpecifiedId(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try{

            //base case
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                //validation check
                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                //resume as normal if passing
                Tracker currentQueryTracker = trackerService.getTrackerById(id);

                if(currentQueryTracker == null){
                    return ResponseEntity.notFound().build();
                }else{
                    return ResponseEntity.ok(currentQueryTracker);
                }
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/trackers/{userid}/all")
    public ResponseEntity<List<Tracker>> getAllUserTrackers(@PathVariable Long userid, @RequestHeader("Authorization") String token){
        try{

            //base case
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);


                //validation
                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                if(JwtUtil.isTokenExpired(jwtToken) == false){
                    log.info("Token was rejected due to expiration date");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                //proceed as normal
                List<Tracker> userTrackers = trackerService.getAllUsersTrackers(userid);



                if(userTrackers.isEmpty()){
                    return ResponseEntity.noContent().build();
                }else{
                    return ResponseEntity.ok(userTrackers);
                }

            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    };

    //Post body will have to follow exact format of the Tracker model, so that data is correctly created
    @PostMapping("/trackers")
    public ResponseEntity<?> createTracker(@RequestBody Tracker tracker, @RequestHeader("Authorization") String token){
        try{
            //base case
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                //Handle unauthorized token
                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                //proceed as normal if passing
                Tracker brandNewTracker = trackerService.createNewTracker(tracker);

                return new ResponseEntity<>(brandNewTracker,HttpStatus.CREATED);
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return new ResponseEntity<>("Failed to create tracker: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    //Endpoint takes in JSON body of a Tracker object, an id of the tracker to select for editing, and token
    @PutMapping("/trackers/edit/{id}")
    public ResponseEntity<?> updateTracker(@RequestBody Tracker tracker, @PathVariable Long id, @RequestHeader("Authorization") String token){
       try{
           //base case
           if(token != null && token.startsWith("Bearer ")){
               String jwtToken = token.substring(7);

               //If validation fails, unauthorized is sent
               if(!JwtUtil.validateToken(jwtToken)){
                   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
               }

               //proceed as normal if all validation passes
               Tracker currentEditingTracker = trackerService.getTrackerById(id);

               if(currentEditingTracker == null){
                   return ResponseEntity.notFound().build();
               }else{
                   currentEditingTracker.setTracker_name(tracker.getTracker_name());
                   currentEditingTracker.setSavings_goal(tracker.getSavings_goal());
                   currentEditingTracker.setNeeds_goal(tracker.getNeeds_goal());
                   currentEditingTracker.setWants_goal(tracker.getWants_goal());
                   currentEditingTracker.setMonth(tracker.isMonth());
                   currentEditingTracker.setYear(tracker.isYear());

                   Tracker postEditingTracker = trackerService.updateTracker(currentEditingTracker);

                   return new ResponseEntity<>(postEditingTracker, HttpStatus.CREATED);
               }
           }else{
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
           }
       }catch(Exception e){
           return new ResponseEntity<>("Failed to update tracker " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }


    @DeleteMapping("trackers/delete/{id}")
    public ResponseEntity<?> deleteTracker(@PathVariable Long id, @RequestHeader("Authorization") String token){
        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                Tracker checkTracker = trackerService.getTrackerById(id);

                if(checkTracker == null){
                    return ResponseEntity.notFound().build();
                }else{
                    MessageResponse message = new MessageResponse("Successfully Deleted");

                    trackerService.deleteTracker(id);

                    return ResponseEntity.ok(message);
                }
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return new ResponseEntity<>("Failed to delete tracker " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
