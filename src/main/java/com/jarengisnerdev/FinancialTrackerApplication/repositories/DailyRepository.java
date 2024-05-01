package com.jarengisnerdev.FinancialTrackerApplication.repositories;

import com.jarengisnerdev.FinancialTrackerApplication.models.Dailys;
import com.jarengisnerdev.FinancialTrackerApplication.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DailyRepository extends JpaRepository<Dailys, Long> {
    @Query("SELECT d FROM Dailys d WHERE d.tracker_id = :trackerId")
    List<Dailys> findAllByTrackerId(@Param("trackerId") Long trackerId);
}
