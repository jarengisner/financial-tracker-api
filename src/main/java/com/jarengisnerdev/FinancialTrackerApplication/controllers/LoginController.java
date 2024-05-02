package com.jarengisnerdev.FinancialTrackerApplication.controllers;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.UserService;
import com.jarengisnerdev.FinancialTrackerApplication.models.LoginRequest;
import com.jarengisnerdev.FinancialTrackerApplication.models.LoginResponse;
import com.jarengisnerdev.FinancialTrackerApplication.models.User;
import com.jarengisnerdev.FinancialTrackerApplication.utility.JwtUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        log.info(username);
        log.info(password);

        User user = userService.getUserByUsername(username);
        Long id = user.getID();

        if(user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(user.verifyPassword(password)){
            String token = JwtUtil.generateToken(username);

            LoginResponse response = new LoginResponse(username, id, token);

            log.info("Login response " + response);

            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
