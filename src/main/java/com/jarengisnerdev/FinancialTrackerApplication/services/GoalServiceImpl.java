package com.jarengisnerdev.FinancialTrackerApplication.services;

import com.jarengisnerdev.FinancialTrackerApplication.interfaces.GoalService;
import com.jarengisnerdev.FinancialTrackerApplication.models.Goal;
import com.jarengisnerdev.FinancialTrackerApplication.repositories.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Override
    public Goal getGoalByGoalId(Long goalId){
        Optional<Goal> currentGoal = goalRepository.findById(goalId);

        return currentGoal.orElse(null);
    };


    @Override
    public List<Goal> getAllGoalsByTrackerId(Long trackerId){
          List<Goal> currentGoalList = goalRepository.findAllByTrackerId(trackerId);

          return currentGoalList;
    };

    @Override
    public Goal createNewGoal(Goal goal){
        return goalRepository.save(goal);
    }
}
