package com.example.demo.controllers;

import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.Family;
import com.example.demo.models.Person;
import com.example.demo.models.Servant;
import com.example.demo.models.Youth;
import com.example.demo.security.Roles;
import com.example.demo.security.SecurityConfig;
import com.example.demo.security.User;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(YouthController.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
//@WebMvcTest(CustomerController.class)
@Import(SecurityConfig.class)
//@ContextConfiguration
@Transactional
class YouthControllerTest {
    //TODO: remove unnecessary authowireds
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private YouthServices youthServices;

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
        // Create a mock CustomUser object
//        User customUser = new User("joseph head","pas",true, Roles.ROLE_Servant_Head,null,true);
        //TODO: find a way to mock the body validator
        doNothing().when(youthServices).addYouth(any(YouthDTO.class));
        mockMvc.perform(post("/youth/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName":"joseph",
                                 "lastName":"shokry",
                                 "phoneNumber":"01579435782"}""")
                        .with(user(users.get(0))))
                .andExpect(status().isCreated());
        verify(youthServices,times(1)).addYouth(any(YouthDTO.class));
    }

    @Test
    void addYouthWithAuthorizedServant() throws Exception {
        doNothing().when(youthServices).addYouth(any(YouthDTO.class));
//        when()
        mockMvc.perform(post("/youth/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "phoneNumber":"01579435782"}""")
                        .with(user(users.get(1))))
                .andExpect(status().isCreated());
        verify(youthServices, times(1)).addYouth(any(YouthDTO.class));
    }

    @Test
    void addYouthWithUnAuthorizedServant() throws Exception {
        doNothing().when(youthServices).addYouth(any(YouthDTO.class));
        mockMvc.perform(post("/youth/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "phoneNumber":"01579435782"}""")
                        .with(user(users.get(2))))
                .andExpect(status().isForbidden());
        verify(youthServices, times(0)).addYouth(any(YouthDTO.class));
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
        when(youthServices.getAll(any(YouthFiltersDTO.class))).thenReturn(null);
        mockMvc.perform(post("/youth/get_all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .with(user(users.get(0))))
                .andExpect(status().isOk());
        verify(youthServices,times(1)).getAll(any(YouthFiltersDTO.class));
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
        when(youthServices.getYouthDtoById(1)).thenReturn(null);
        mockMvc.perform(get("/youth/get")
                        .param("youthId","1")
                        .with(user(users.get(0))))
                .andExpect(status().isOk());
        verify(youthServices, times(1)).getYouthDtoById(1);
    }

    @Test
    void getYouthWithAuthorizedServant() throws Exception {
        Youth youth = new Youth();
        youth.setFamily(families.get(0));
        when(youthServices.getYouthDtoById(1)).thenReturn(null);
        when(youthServices.getYouthById(1)).thenReturn(youth);
        mockMvc.perform(get("/youth/get")
                        .param("youthId", "1")
                        .with(user(users.get(1))))
                .andExpect(status().isOk());
        verify(youthServices, times(1)).getYouthDtoById(1);
        verify(youthServices, times(1)).getYouthById(1);
    }

    @Test
    void getYouthWithUnAuthorizedServant() throws Exception {
        Youth youth = new Youth();
        youth.setFamily(families.get(0));
        when(youthServices.getYouthById(1)).thenReturn(youth);
        when(youthServices.getYouthDtoById(1)).thenReturn(null);
        mockMvc.perform(get("/youth/get")
                        .param("youthId", "1")
                        .with(user(users.get(2))))
                .andExpect(status().isForbidden());
        verify(youthServices, times(0)).getYouthDtoById(1);
        verify(youthServices, times(1)).getYouthById(1);
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
        doNothing().when(youthServices).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .with(user(users.get(0))))
                .andExpect(status().isOk());
        verify(youthServices,times(1)).editYouth(any(YouthDTO.class));
    }

    @Test
    void editYouthWithAuthorizedServant() throws Exception {
        doNothing().when(youthServices).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "familyId":"1"
                                }""")
                        .with(user(users.get(1))))
                .andExpect(status().isOk());
        verify(youthServices, times(1)).editYouth(any(YouthDTO.class));
    }

    @Test
    void editYouthWithUnAuthorizedServant() throws Exception {
        doNothing().when(youthServices).editYouth(any(YouthDTO.class));
        mockMvc.perform(patch("/youth/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "familyId":"1"
                                }""")
                        .with(user(users.get(2))))
                .andExpect(status().isForbidden());
        verify(youthServices, times(0)).editYouth(any(YouthDTO.class));
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

/*

System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii");
        FamilyDTO familyDTO = new FamilyDTO("mark",1,null,null);
        familyServices.addFamily(familyDTO);
        ServantDTO servantDTO = new ServantDTO(null,"joseph",1,null);
        servantServices.addServant(servantDTO);
//        when(servantServices.getServantById(any())).thenReturn(new Servant(new Family()));
        UserDTO userDTO = new UserDTO("joseph servant", "pas",true,null, Roles.ROLE_Servant,1);
        userService.addUser(userDTO);
 */