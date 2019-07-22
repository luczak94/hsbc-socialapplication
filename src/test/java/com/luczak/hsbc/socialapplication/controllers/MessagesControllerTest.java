package com.luczak.hsbc.socialapplication.controllers;

import com.luczak.hsbc.socialapplication.model.User;
import com.luczak.hsbc.socialapplication.services.MessageService;
import com.luczak.hsbc.socialapplication.services.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest({UserController.class, MessagesController.class})

public class MessagesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private MessageService tweetService;

    @Before
    public void setUp() throws Exception {
        Mockito.when(userService.getUser(Mockito.anyString())).thenReturn(Optional.of(new User("user")));

    }

    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetUserByNick() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{username}", "username"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetUserWall() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/{username}/wall", "username"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));


    }

    @Test
    public void testGetUserTimeline() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/{username}/timeline", "username"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));


    }

}