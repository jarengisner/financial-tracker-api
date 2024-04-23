package com.jarengisnerdev.FinancialTrackerApplication.interfaces;

import com.jarengisnerdev.FinancialTrackerApplication.models.User;

public interface UserService {

    User getUserById(Long userID);

    User getUserByUsername(String username);

    User createUser(User user);

    void deleteUser(Long userID);
}
