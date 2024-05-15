package com.jarengisnerdev.FinancialTrackerApplication.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarengisnerdev.FinancialTrackerApplication.models.Goal;
import com.jarengisnerdev.FinancialTrackerApplication.utility.JwtUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class GoalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String username;

    private static String token;

    @BeforeAll
    public static void setup(){
        username = "testCaseUser";
        token = JwtUtil.generateToken(username);
    }

    @Test
    public void testGoalById() throws Exception{
        int id = 1;

        mockMvc.perform(MockMvcRequestBuilders.get("/goals/{id}", id)
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.goal_id").value(1));
    }


    @Test
    public void getAllGoalsByTrackerTesting() throws Exception{
        int id = 2;

        mockMvc.perform(MockMvcRequestBuilders.get("/goals/{id}/all", id)
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tracker_id").value(2));
    }

}
