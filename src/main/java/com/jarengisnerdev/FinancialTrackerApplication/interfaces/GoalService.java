package com.jarengisnerdev.FinancialTrackerApplication.interfaces;

import com.jarengisnerdev.FinancialTrackerApplication.models.Goal;
import org.springframework.stereotype.Service;

import java.util.List;



public interface GoalService {

    public Goal getGoalByGoalId(Long goalId);

    public List<Goal> getAllGoalsByTrackerId(Long trackerId);

    public Goal createNewGoal(Goal goal);

    public Goal updateGoal(Goal goal);

    public void deleteGoal(Long goalId);
}
