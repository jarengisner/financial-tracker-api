package com.jarengisnerdev.FinancialTrackerApplication.repositories;

import com.jarengisnerdev.FinancialTrackerApplication.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    @Query("SELECT g FROM Goal g WHERE g.tracker_id = :trackerId")
    List<Goal> findAllByTrackerId(@Param("trackerId") Long trackerId);
}
