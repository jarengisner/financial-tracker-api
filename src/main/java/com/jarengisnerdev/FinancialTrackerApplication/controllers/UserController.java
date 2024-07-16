package com.jarengisnerdev.FinancialTrackerApplication.controllers;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.UserService;
import com.jarengisnerdev.FinancialTrackerApplication.models.User;
import com.jarengisnerdev.FinancialTrackerApplication.utility.JwtUtil;
import com.jarengisnerdev.FinancialTrackerApplication.utility.MessageResponse;
import io.jsonwebtoken.Jwt;
import lombok.extern.java.Log;
import org.apache.coyote.Response;
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

    @PutMapping("/users/update/password/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id, @RequestHeader("Authorization") String token){
        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                User userToEdit = userService.getUserById(id);

                if(userToEdit == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }

                userToEdit.hashedPasswordUpdate(user.getPassword());
                User postEditUser = userService.updateUser(userToEdit);
                return ResponseEntity.ok("Successfully updated user");
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return new ResponseEntity<>("Failed to update user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    };

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, @RequestHeader("Authorization") String token){
        try{
            if(token != null && token.startsWith("Bearer ")){
                String jwtToken = token.substring(7);

                if(!JwtUtil.validateToken(jwtToken)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                User userToDelete = userService.getUserById(id);

                if(userToDelete == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }

                userService.deleteUser(id);

                MessageResponse message = new MessageResponse("User successfully deleted");

                return ResponseEntity.ok(message);
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            return new ResponseEntity<>("Failed to delete user account " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
