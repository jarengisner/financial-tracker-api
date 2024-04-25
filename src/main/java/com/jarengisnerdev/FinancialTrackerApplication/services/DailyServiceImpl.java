package com.jarengisnerdev.FinancialTrackerApplication.services;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.DailyService;
import com.jarengisnerdev.FinancialTrackerApplication.models.Daily;
import com.jarengisnerdev.FinancialTrackerApplication.repositories.DailyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DailyServiceImpl implements DailyService {

    @Autowired
    DailyRepository dailyRepository;

    @Override
    public Daily getDailyById(Long dailyId){
        Optional<Daily> currentDaily = dailyRepository.findById(dailyId);

        return currentDaily.orElse(null);
    }

    @Override
    public List<Daily> getAllDailyByTrackerId(Long trackerId){
        List<Daily> currentDailyList = dailyRepository.findAllByTrackerId(trackerId);

        return currentDailyList;
    };


    @Override
    public Daily createNewDaily(Daily daily){
        return dailyRepository.save(daily);
    };

    @Override
    public Daily updateDaily(Daily daily){
        return dailyRepository.save(daily);
    };
}
