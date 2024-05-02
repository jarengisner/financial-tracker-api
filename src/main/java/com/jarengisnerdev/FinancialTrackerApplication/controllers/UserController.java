package com.jarengisnerdev.FinancialTrackerApplication.controllers;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.UserService;
import com.jarengisnerdev.FinancialTrackerApplication.models.User;
import com.jarengisnerdev.FinancialTrackerApplication.utility.JwtUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.cert.CRLException;

@Log
@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users/{userID}")
    public ResponseEntity<User> getUser(@PathVariable Long userID, @RequestHeader("Authorization") String token){
        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                boolean validation = JwtUtil.validateToken(jwtToken);

                if(!validation){
                    //log.info("request stopped");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                User currentUser = userService.getUserById(userID);

                if(currentUser == null){
                    return ResponseEntity.notFound().build();
                }else{
                    return ResponseEntity.ok(currentUser);
                }

            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    };


    @GetMapping("/users/name/{username}")
    public ResponseEntity<User> getUserByName(@PathVariable String username, @RequestHeader("Authorization") String token){
        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);
                //log.info(jwtToken);

                boolean validation = JwtUtil.validateToken(jwtToken);

                if(!validation){
                    //log.info("request stopped");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                User currentUser = userService.getUserByUsername(username);

                if(currentUser == null){
                    return ResponseEntity.notFound().build();
                }else{
                    return ResponseEntity.ok(currentUser);
                }

            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newlyCreatedUser = userService.createUser(user);
        return new ResponseEntity<>(newlyCreatedUser, HttpStatus.CREATED);
    }
}
