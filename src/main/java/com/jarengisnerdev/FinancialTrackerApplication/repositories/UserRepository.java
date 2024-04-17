package com.jarengisnerdev.FinancialTrackerApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jarengisnerdev.FinancialTrackerApplication.models.User;

//The <User, Long> is simply declaring that this manages the User Entity and is identifiable by the Long type PKey
public interface UserRepository extends JpaRepository<User, Long> {

}
