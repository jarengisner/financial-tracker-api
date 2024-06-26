package com.jarengisnerdev.FinancialTrackerApplication.controllerTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.jarengisnerdev.FinancialTrackerApplication.utility.JwtUtil;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

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
    public void testUserById() throws Exception{
        int id = 2;
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", id)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.username").value("jaren"));
    }

    @Test
    public void testUserByName() throws Exception{
        String name = "jarenfinaltest";
        mockMvc.perform(MockMvcRequestBuilders.get("/users/name/{name}", name)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.username").value("jarenfinaltest"));
    }
}
