package com.jarengisnerdev.FinancialTrackerApplication.interfaces;

import com.jarengisnerdev.FinancialTrackerApplication.models.User;

public interface UserService {

    User getUserById(Long userID);

    User createUser(User user);

    void deleteUser(Long userID);
}
