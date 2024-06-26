package com.jarengisnerdev.FinancialTrackerApplication.interfaces;

import com.jarengisnerdev.FinancialTrackerApplication.models.Tracker;
import com.jarengisnerdev.FinancialTrackerApplication.utility.MessageResponse;

import java.util.List;

public interface TrackerService {

    public Tracker getTrackerById(Long trackerID);

    /*This will most likely be the very first service we call, being as it
    * will give us access to essentially the rest of the application, post log in*/
    public List<Tracker> getAllUsersTrackers(Long userID);

    public Tracker createNewTracker(Tracker tracker);

    public Tracker updateTracker(Tracker tracker);

    public void deleteTracker(Long trackerID);

}
