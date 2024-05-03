package com.jarengisnerdev.FinancialTrackerApplication.controllers;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.DailyService;
import com.jarengisnerdev.FinancialTrackerApplication.models.Dailys;
import com.jarengisnerdev.FinancialTrackerApplication.utility.JwtUtil;
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
    public ResponseEntity<Dailys> getDailyById(@PathVariable Long id, @RequestHeader("Authorization") String token){

        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                Dailys currentQueryDaily = dailyService.getDailyById(id);

                if(currentQueryDaily == null){
                    return ResponseEntity.notFound().build();
                }else{
                    return ResponseEntity.ok(currentQueryDaily);
                }

            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    };

    @GetMapping("/daily/{trackerId}/all")
    public ResponseEntity<List<Dailys>> getAllDailyByTrackerId(@PathVariable Long trackerId, @RequestHeader("Authorization") String token){
        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                List<Dailys> currentQueryDailyList = dailyService.getAllDailyByTrackerId(trackerId);

                if(currentQueryDailyList.isEmpty()){
                    return ResponseEntity.noContent().build();
                }else{
                    return ResponseEntity.ok(currentQueryDailyList);
                }

            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    };

    //date format must follow YYYY-MM-DD format
    @PostMapping("/daily")
    public ResponseEntity<?> createNewDaily(@RequestBody Dailys daily, @RequestHeader("Authorization") String token){
        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                Dailys brandNewDaily = dailyService.createNewDaily(daily);

                return new ResponseEntity<>(brandNewDaily,HttpStatus.CREATED);
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return new ResponseEntity<>("Failed to create new Daily" + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/daily/update/{id}")
    public ResponseEntity<?> updateDailyById(@PathVariable Long id, @RequestBody Dailys daily, @RequestHeader("Authorization") String token){
        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                Dailys currentDailyToEdit = dailyService.getDailyById(id);

                if(currentDailyToEdit == null){
                    return ResponseEntity.notFound().build();
                }else {
                    currentDailyToEdit.setSaved(daily.getSaved());
                    currentDailyToEdit.setSavedNote(daily.getSavedNote());
                    currentDailyToEdit.setNeeds(daily.getNeeds());
                    currentDailyToEdit.setNeedsNote(daily.getNeedsNote());
                    currentDailyToEdit.setWants(daily.getWants());
                    currentDailyToEdit.setWantsNote(daily.getWantsNote());

                    Dailys dailyAfterEditing = dailyService.updateDaily(currentDailyToEdit);
                    return new ResponseEntity<>(dailyAfterEditing, HttpStatus.CREATED);
                }

            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return new ResponseEntity<>("Failed to update daily record: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }




    };


}
