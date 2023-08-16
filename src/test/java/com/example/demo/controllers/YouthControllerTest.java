package com.example.demo.controllers;

import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.security.SecurityConfig;
import com.example.demo.services.YouthServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.WebDataBinder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(YouthController.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
//@WebMvcTest(CustomerController.class)
@Import(SecurityConfig.class)
//@ContextConfiguration
class YouthControllerTest {
    //TODO: i think there is a problem because i use a custom user class and all this test uses the default user class
    @Autowired
    private MockMvc mockMvc;
    @MockBean private YouthServices youthServices;

    //test the add youth
    @Test
    @WithMockUser(roles = "Servant_Head")
    void addYouthWithAuthorizedServantHead() throws Exception {
        //TODO: find a way to mock the body validator
        doNothing().when(youthServices).addYouth(any(YouthDTO.class));
        mockMvc.perform(post("/youth/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"مينا\",\n" +
                                "    \"lastName\":\"اسامه\",\n" +
                                "    \"phoneNumber\":\"01579435782\"}"))
                .andExpect(status().isCreated());
        verify(youthServices,times(1)).addYouth(any(YouthDTO.class));
    }

    @Test
    @WithMockUser(roles = "Servant")
    void addYouthWithAuthorizedServant() throws Exception {


    }
    @Test
    @WithMockUser(roles = "Servant_Head")
    void addYouthWithUnAuthorizedServant() throws Exception {

    }
    @Test
    @WithAnonymousUser
    void addYouthWithUnAuthenticatedUser() throws Exception {
        mockMvc.perform(post("/youth/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }
/////////////////// test the get all
    @Test
    @WithMockUser(roles = "Servant_Head")
    void getAllWithHeadServant() throws Exception {
        when(youthServices.getAll(any(YouthFiltersDTO.class))).thenReturn(null);
        mockMvc.perform(post("/youth/get_all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
        verify(youthServices,times(1)).getAll(any(YouthFiltersDTO.class));
    }

    @Test
    @WithMockUser(roles = "Servant")
    void getAllWithUnauthorizedServant() throws Exception {
        mockMvc.perform(post("/youth/get_all"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void getAllWithUnauthenticatedUser() throws Exception {
        mockMvc.perform(post("/youth/get_all"))
                .andExpect(status().isForbidden());
    }
/////////////////test the get youth

    @Test
    @WithMockUser(roles = "Servant_Head")
    void getYouthWithAuthorizedServantHead() throws Exception {
        when(youthServices.getYouthDtoById(1)).thenReturn(null);
        mockMvc.perform(get("/youth/get")
                        .param("youthId","1"))
                .andExpect(status().isOk());
        verify(youthServices, times(1)).getYouthDtoById(1);
    }
    @Test
    @WithMockUser(roles = "Servant_Head")
    void getYouthWithAuthorizedServant() throws Exception {

    }
    @Test
    @WithMockUser(roles = "Servant_Head")
    void getYouthWithUnAuthorizedServant() throws Exception {

    }
    @Test
    @WithAnonymousUser
    void getYouthWithUnAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/youth/get")
                        .param("youthId","1"))
                .andExpect(status().isUnauthorized());
    }

//////////// test the edit youth
    @Test
    @WithMockUser(roles = "Servant_Head")
    void editYouthWithAuthorizedServantHead() throws Exception {
        doNothing().when(youthServices).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
        verify(youthServices,times(1)).editYouth(any(YouthDTO.class));
    }
    @Test
    @WithMockUser(roles = "Servant_Head")
    void editYouthWithAuthorizedServant() throws Exception {

    }
    @Test
    @WithMockUser(roles = "Servant_Head")
    void editYouthWithUnAuthorizedServant() throws Exception {

    }
    @Test
    @WithAnonymousUser
    void editYouthWithUnAuthenticatedUser() throws Exception {
        mockMvc.perform(post("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }
}