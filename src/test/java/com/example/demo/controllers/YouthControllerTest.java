package com.example.demo.controllers;

import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.entities.Family;
import com.example.demo.models.entities.Person;
import com.example.demo.models.entities.Servant;
import com.example.demo.models.entities.Youth;
import com.example.demo.security.Roles;
import com.example.demo.models.entities.User;
import com.example.demo.services.implementations.YouthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class YouthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private YouthService youthService;

    List<Family> families = List.of(
            new Family(1, "mark",null,null,null,null),
            new Family(2, "john",null,null,null,null)
            );

    List<Person> persons = List.of(
            new Servant(families.get(0)),
            new Servant(families.get(1))
    );

    List<User> users = List.of(
            new User("head","pas",true, Roles.ROLE_Servant_Head,null,true),
            new User("servant 1","pas",true, Roles.ROLE_Servant,persons.get(0),true),
            new User("servant 2","pas",true, Roles.ROLE_Servant,persons.get(1),true)
            );

    //test the add youth
    @Test
    void addYouthWithAuthorizedServantHead() throws Exception {
        //TODO: find a way to mock the body validator
        doNothing().when(youthService).addYouth(any(YouthDTO.class));
        mockMvc.perform(post("/youth/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName":"joseph",
                                 "lastName":"shokry",
                                 "phoneNumber":"01579435782",
                                 "familyId":1}""")
                        .with(user(users.get(0))))
                .andExpect(status().isCreated());
        verify(youthService,times(1)).addYouth(any(YouthDTO.class));
    }

    @Test
    void addYouthWithAuthorizedServant() throws Exception {
        doNothing().when(youthService).addYouth(any(YouthDTO.class));
        mockMvc.perform(post("/youth/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "phoneNumber":"01579435782"}""")
                        .with(user(users.get(1))))
                .andExpect(status().isCreated());
        verify(youthService, times(1)).addYouth(any(YouthDTO.class));
    }

    @Test
    void addYouthWithUnAuthorizedServant() throws Exception {
        doNothing().when(youthService).addYouth(any(YouthDTO.class));
        mockMvc.perform(post("/youth/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "phoneNumber":"01579435782"}""")
                        .with(user(users.get(2))))
                .andExpect(status().isForbidden());
        verify(youthService, times(0)).addYouth(any(YouthDTO.class));
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
    void getAllWithHeadServant() throws Exception {
        when(youthService.getAll(any(YouthFiltersDTO.class))).thenReturn(null);
        mockMvc.perform(post("/youth/get_all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .with(user(users.get(0))))
                .andExpect(status().isOk());
        verify(youthService,times(1)).getAll(any(YouthFiltersDTO.class));
    }

    @Test
    void getAllWithUnauthorizedServant() throws Exception {
        mockMvc.perform(post("/youth/get_all")
                        .with(user(users.get(1))))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithAnonymousUser
    void getAllWithUnauthenticatedUser() throws Exception {
        mockMvc.perform(post("/youth/get_all"))
                .andExpect(status().isUnauthorized());
    }
/////////////////test the get youth

    @Test
    void getYouthWithAuthorizedServantHead() throws Exception {
        when(youthService.getYouthDtoById(1)).thenReturn(null);
        mockMvc.perform(get("/youth/get")
                        .param("youthId","1")
                        .with(user(users.get(0))))
                .andExpect(status().isOk());
        verify(youthService, times(1)).getYouthDtoById(1);
    }

    @Test
    void getYouthWithAuthorizedServant() throws Exception {
        Youth youth = new Youth();
        youth.setFamily(families.get(0));
        when(youthService.getYouthDtoById(1)).thenReturn(null);
        when(youthService.getYouthById(1)).thenReturn(youth);
        mockMvc.perform(get("/youth/get")
                        .param("youthId", "1")
                        .with(user(users.get(1))))
                .andExpect(status().isOk());
        verify(youthService, times(1)).getYouthDtoById(1);
        verify(youthService, times(1)).getYouthById(1);
    }

    @Test
    void getYouthWithUnAuthorizedServant() throws Exception {
        Youth youth = new Youth();
        youth.setFamily(families.get(0));
        when(youthService.getYouthById(1)).thenReturn(youth);
        when(youthService.getYouthDtoById(1)).thenReturn(null);
        mockMvc.perform(get("/youth/get")
                        .param("youthId", "1")
                        .with(user(users.get(2))))
                .andExpect(status().isForbidden());
        verify(youthService, times(0)).getYouthDtoById(1);
        verify(youthService, times(1)).getYouthById(1);
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
    void editYouthWithAuthorizedServantHead() throws Exception {
        doNothing().when(youthService).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "phoneNumber":"01579435782"}""")
                        .with(user(users.get(0))))
                .andExpect(status().isOk());
        verify(youthService,times(1)).editYouth(any(YouthDTO.class));
    }

    @Test
    void editYouthWithAuthorizedServant() throws Exception {
        doNothing().when(youthService).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "phoneNumber":"01579435782"}""")
                        .with(user(users.get(1))))
                .andExpect(status().isOk());
        verify(youthService, times(1)).editYouth(any(YouthDTO.class));
    }

    @Test
    void editYouthWithUnAuthorizedServant() throws Exception {
        doNothing().when(youthService).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "phoneNumber":"01579435782"}""")
                        .with(user(users.get(2))))
                .andExpect(status().isForbidden());
        verify(youthService, times(0)).editYouth(any(YouthDTO.class));
    }

    @Test
    @WithAnonymousUser
    void editYouthWithUnAuthenticatedUser() throws Exception {
        mockMvc.perform(post("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "phoneNumber":"01579435782"}"""))
                .andExpect(status().isUnauthorized());
    }
/////////////// exceptions
    @Test
    void editYouthWithNonHeadServantWithNoFamilyId() throws Exception {
        doNothing().when(youthService).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(users.get(1)))
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "phoneNumber":"01579435782"}"""))
                .andExpect(status().isBadRequest());
        verify(youthService, times(0)).editYouth(any(YouthDTO.class));
    }

    @Test
    void editYouthWithHeadServantWithNoFamilyId() throws Exception {
        doNothing().when(youthService).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(users.get(0)))
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "phoneNumber":"01579435782"}"""))
                .andExpect(status().isBadRequest());
        verify(youthService, times(0)).editYouth(any(YouthDTO.class));
    }

    @Test
    void editYouthWithNonHeadServantWithNoFirstName() throws Exception {
        doNothing().when(youthService).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(users.get(1)))
                        .content("""
                                {  "lastName":"shokry",
                                   "phoneNumber":"01579435782"}"""))
                .andExpect(status().isBadRequest());
        verify(youthService, times(0)).editYouth(any(YouthDTO.class));
    }
    @Test
    void editYouthWithNonHeadServantWithNoLastName() throws Exception {
        doNothing().when(youthService).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(users.get(1)))
                        .content("""
                                {  "firstName":"joseph",
                                   "phoneNumber":"01579435782"}"""))
                .andExpect(status().isBadRequest());
        verify(youthService, times(0)).editYouth(any(YouthDTO.class));
    }
    @Test
    void editYouthWithNonHeadServantWithNoPhoneNumber() throws Exception {
        doNothing().when(youthService).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(users.get(1)))
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry"}"""))
                .andExpect(status().isBadRequest());
        verify(youthService, times(0)).editYouth(any(YouthDTO.class));
    }
    @Test
    void editYouthWithNonHeadServantWithNoBody() throws Exception {
        doNothing().when(youthService).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .with(user(users.get(1))))
                .andExpect(status().isBadRequest());
        verify(youthService, times(0)).editYouth(any(YouthDTO.class));
    }


}
