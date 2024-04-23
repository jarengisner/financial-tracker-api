package com.jarengisnerdev.FinancialTrackerApplication.repositories;

import com.jarengisnerdev.FinancialTrackerApplication.models.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrackerRepository extends JpaRepository<Tracker, Long> {

    //This custom method should check for all t in Tracker where the user_id matches
    @Query("SELECT t FROM Tracker t WHERE t.user_id = :userId")
    List<Tracker> findAllByUserId(@Param("userId") Long userId);
}
