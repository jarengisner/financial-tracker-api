package com.jarengisnerdev.FinancialTrackerApplication.services;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.TrackerService;
import com.jarengisnerdev.FinancialTrackerApplication.models.Tracker;
import com.jarengisnerdev.FinancialTrackerApplication.repositories.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackerServiceImpl implements TrackerService {

    @Autowired
    private TrackerRepository trackerRepository;

    @Override
    public Tracker getTrackerById(Long trackerID){
        Optional<Tracker> currentTracker = trackerRepository.findById(trackerID);

        return currentTracker.orElse(null);
    };

    @Override
    public List<Tracker> getAllUsersTrackers(Long userID){
        List<Tracker> currentTrackerList = trackerRepository.findAllByUserId(userID);

        return currentTrackerList;
    };

    @Override
    public Tracker createNewTracker(Tracker tracker){
        return trackerRepository.save(tracker);
    }

    @Override
    public Tracker updateTracker(Tracker tracker){return trackerRepository.save(tracker);}
}
