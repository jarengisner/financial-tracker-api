package com.jarengisnerdev.FinancialTrackerApplication.controllerTests;

import com.jarengisnerdev.FinancialTrackerApplication.utility.JwtUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class TrackerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String username;
    private static String token;

    @BeforeAll
    public static void setup(){
        username = "testCaseUser";
        token = JwtUtil.generateToken(username);
    };

    @Test
    public void getTrackerById() throws Exception{
        int id=2;

        mockMvc.perform(MockMvcRequestBuilders.get("/trackers/{id}", id)
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tracker_id").value(2));
    }

    @Test
    public void getAllTrackersByUserId() throws Exception{
        int id = 2;

        mockMvc.perform(MockMvcRequestBuilders.get("/trackers/{id}/all", id)
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user_id").value(2));
    }


}
