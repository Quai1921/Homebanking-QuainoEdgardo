package com.mindhub.homebanking.models;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindhub.homebanking.dtos.LoginDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.http.HttpHeaders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private String token;
//
//
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        LoginDTO loginDTO = new LoginDTO("melba@mindhub.com", "Melba1234");
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        token = mockMvc.perform(
//                post("/api/auth/login")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(loginDTO)
//                        )
//                ).andReturn()
//                 .getResponse()
//                 .getContentAsString();
//    }
//
//
//    @Test
//    void testLogin() throws Exception {
//
//        LoginDTO loginDTO = new LoginDTO("melba@mindhub.com", "Melba1234");
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        mockMvc.perform(
//                post("/api/auth/login")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(loginDTO)
//                        )
//        ).andDo(print())
//                .andExpect(status().is(200))
//                .andExpect(content().string(containsString(".")))
//                .andExpect(content().string(matchesPattern("([a-zA-Z_]).+")));
//
//
//    }
//
//    @Test
//    void testNotLogin() throws Exception {
//
//        LoginDTO loginDTO = new LoginDTO("melba@mindhub.com", "Melba");
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        mockMvc.perform(
//                        post("/api/auth/login")
//                                .contentType("application/json")
//                                .content(objectMapper.writeValueAsString(loginDTO)
//                                )
//                ).andDo(print())
//                .andExpect(status().is(403))
//                .andExpect(content().string(containsString("The email or password entered is not valid")));
//    }


//    @Test
//    public void getClientCurrent(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " +(token);
//        mockMvc.perform(
//                post("/api/auth/login")
//                        .contentType("application/json")
//                        .headers(headers)
//        );
//    }




}
