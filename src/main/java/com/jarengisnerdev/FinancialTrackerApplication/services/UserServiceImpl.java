package com.jarengisnerdev.FinancialTrackerApplication.services;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.UserService;
import com.jarengisnerdev.FinancialTrackerApplication.models.User;
import com.jarengisnerdev.FinancialTrackerApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long userID){
        Optional<User> userOptional = userRepository.findById(userID);
        return userOptional.orElse(null); // Return the User or null if not found
    };

    @Override
    public User createUser(User user){
        return userRepository.save(user);
    };
    @Override
    public void deleteUser(Long userID){
        userRepository.deleteById(userID);
    };
}
