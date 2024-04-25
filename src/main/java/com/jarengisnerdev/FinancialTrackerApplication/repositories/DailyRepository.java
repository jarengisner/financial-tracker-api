package com.jarengisnerdev.FinancialTrackerApplication.repositories;

import com.jarengisnerdev.FinancialTrackerApplication.models.Daily;
import com.jarengisnerdev.FinancialTrackerApplication.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DailyRepository extends JpaRepository<Daily, Long> {
    @Query("SELECT d FROM Daily d WHERE d.tracker_id = :trackerId")
    List<Daily> findAllByTrackerId(@Param("trackerId") Long trackerId);
}
