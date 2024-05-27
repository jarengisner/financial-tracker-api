package com.jarengisnerdev.FinancialTrackerApplication.interfaces;

import com.jarengisnerdev.FinancialTrackerApplication.models.Dailys;

import java.util.List;

public interface DailyService {

    public Dailys getDailyById(Long dailyId);

    public List<Dailys> getAllDailyByTrackerId(Long trackerId);

    public Dailys createNewDaily(Dailys daily);

    public Dailys updateDaily(Dailys daily);

    public void deleteDaily(Long dailyId);
}
