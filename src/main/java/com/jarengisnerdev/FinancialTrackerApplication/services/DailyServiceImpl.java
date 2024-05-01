package com.jarengisnerdev.FinancialTrackerApplication.services;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.DailyService;
import com.jarengisnerdev.FinancialTrackerApplication.models.Dailys;
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
    public Dailys getDailyById(Long dailyId){
        Optional<Dailys> currentDaily = dailyRepository.findById(dailyId);

        return currentDaily.orElse(null);
    }

    @Override
    public List<Dailys> getAllDailyByTrackerId(Long trackerId){
        List<Dailys> currentDailyList = dailyRepository.findAllByTrackerId(trackerId);

        return currentDailyList;
    };


    @Override
    public Dailys createNewDaily(Dailys daily){
        return dailyRepository.save(daily);
    };

    @Override
    public Dailys updateDaily(Dailys daily){
        return dailyRepository.save(daily);
    };
}
