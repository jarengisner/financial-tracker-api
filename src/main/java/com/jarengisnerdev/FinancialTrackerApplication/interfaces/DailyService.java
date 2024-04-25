package com.jarengisnerdev.FinancialTrackerApplication.interfaces;

import com.jarengisnerdev.FinancialTrackerApplication.models.Daily;

import java.util.List;

public interface DailyService {

    public Daily getDailyById(Long dailyId);

    public List<Daily> getAllDailyByTrackerId(Long trackerId);

    public Daily createNewDaily(Daily daily);

    public Daily updateDaily(Daily daily);
}
