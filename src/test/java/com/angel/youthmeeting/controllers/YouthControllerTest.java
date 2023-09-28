package com.angel.youthmeeting.controllers;

import com.angel.youthmeeting.dataProviders.YouthControllerDataProvider;
import com.angel.youthmeeting.models.dtos.YouthDTO;
import com.angel.youthmeeting.models.dtos.YouthFiltersDTO;
import com.angel.youthmeeting.services.implementations.YouthService;
import com.angel.youthmeeting.models.entities.Family;
import com.angel.youthmeeting.models.entities.User;
import com.angel.youthmeeting.models.entities.Youth;
import com.angel.youthmeeting.util.security.EndPoints;
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

    private final List<Family> families;
    private final List<User> users;

    public YouthControllerTest(@Autowired final YouthControllerDataProvider youthControllerDataProvider) {
        families = youthControllerDataProvider.getFamiliesTable();
        users = youthControllerDataProvider.getUsersTable();
    }

    //test the add youth
    @Test
    void addYouthWithAuthorizedServantHead() throws Exception {
        //TODO: find a way to mock the body validator
    when(youthService.addYouth(any(YouthDTO.class))).thenReturn(true);
        mockMvc.perform(post(EndPoints.YOUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName":"joseph",
                                 "lastName":"shokry",
                                 "phoneNumber":"01579435782",
                                 "gender":"MALE",
                                 "familyId":1}""")
                        .with(user(users.get(0))))
                .andExpect(status().isCreated());
        verify(youthService,times(1)).addYouth(any(YouthDTO.class));
    }

    @Test
    void addYouthWithAuthorizedServant() throws Exception {
    when(youthService.addYouth(any(YouthDTO.class))).thenReturn(true);
        mockMvc.perform(post(EndPoints.YOUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "gender":"MALE",
                                   "phoneNumber":"01579435782"}""")
                        .with(user(users.get(1))))
                .andExpect(status().isCreated());
        verify(youthService, times(1)).addYouth(any(YouthDTO.class));
    }

    @Test
    void addYouthWithUnAuthorizedServant() throws Exception {
        when(youthService.addYouth(any(YouthDTO.class))).thenReturn(true);
        mockMvc.perform(post(EndPoints.YOUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "gender":"MALE",
                                   "phoneNumber":"01579435782"}""")
                        .with(user(users.get(2))))
                .andExpect(status().isForbidden());
        verify(youthService, times(0)).addYouth(any(YouthDTO.class));
    }
    @Test
    @WithAnonymousUser
    void addYouthWithUnAuthenticatedUser() throws Exception {
        mockMvc.perform(post(EndPoints.YOUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }
/////////////////// test the get all
    @Test
    void getAllWithHeadServant() throws Exception {
        when(youthService.findAll(any(YouthFiltersDTO.class))).thenReturn(null);
        mockMvc.perform(post(EndPoints.YOUTH + EndPoints.YOUTH_GET_ALL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .with(user(users.get(0))))
                .andExpect(status().isOk());
        verify(youthService,times(1)).findAll(any(YouthFiltersDTO.class));
    }

    @Test
    void getAllWithUnauthorizedServant() throws Exception {
        mockMvc.perform(post(EndPoints.YOUTH + EndPoints.YOUTH_GET_ALL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .with(user(users.get(1))))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithAnonymousUser
    void getAllWithUnauthenticatedUser() throws Exception {
        mockMvc.perform(post(EndPoints.YOUTH + EndPoints.YOUTH_GET_ALL))
                .andExpect(status().isUnauthorized());
    }
/////////////////test the get youth

    @Test
    void getYouthWithAuthorizedServantHead() throws Exception {
        when(youthService.findYouthDtoById(1L)).thenReturn(null);
        mockMvc.perform(get(EndPoints.YOUTH + EndPoints.GET_YOUTH, 1L)
                        .with(user(users.get(0))))
                .andExpect(status().isOk());
        verify(youthService, times(1)).findYouthDtoById(1L);
    }

    @Test
    void getYouthWithAuthorizedServant() throws Exception {
        Youth youth = new Youth();
        youth.setFamily(families.get(0));
        when(youthService.findYouthDtoById(1L)).thenReturn(null);
        when(youthService.findYouthById(1L)).thenReturn(youth);
        mockMvc.perform(get(EndPoints.YOUTH + EndPoints.GET_YOUTH, 1L)
                        .with(user(users.get(1))))
                .andExpect(status().isOk());
        verify(youthService, times(1)).findYouthDtoById(1L);
        verify(youthService, times(1)).findYouthById(1L);
    }

    @Test
    void getYouthWithUnAuthorizedServant() throws Exception {
        Youth youth = new Youth();
        youth.setFamily(families.get(0));
        when(youthService.findYouthById(1L)).thenReturn(youth);
        when(youthService.findYouthDtoById(1L)).thenReturn(null);
        mockMvc.perform(get(EndPoints.YOUTH + EndPoints.GET_YOUTH, 1L)
                        .with(user(users.get(2))))
                .andExpect(status().isForbidden());
        verify(youthService, times(0)).findYouthDtoById(1L);
        verify(youthService, times(1)).findYouthById(1L);
    }
    @Test
    @WithAnonymousUser
    void getYouthWithUnAuthenticatedUser() throws Exception {
        mockMvc.perform(get(EndPoints.YOUTH)
                        .param("youthId","1"))
                .andExpect(status().isUnauthorized());
    }

//////////// test the edit youth
    @Test
    void editYouthWithAuthorizedServantHead() throws Exception {
        when(youthService.editYouth(any(YouthDTO.class))).thenReturn(true);
        mockMvc.perform(patch(EndPoints.YOUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "gender":"MALE",
                                   "phoneNumber":"01579435782"}""")
                        .with(user(users.get(0))))
                .andExpect(status().isOk());
        verify(youthService,times(1)).editYouth(any(YouthDTO.class));
    }

    @Test
    void editYouthWithAuthorizedServant() throws Exception {
        when(youthService.editYouth(any(YouthDTO.class))).thenReturn(true);
        mockMvc.perform(patch(EndPoints.YOUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "gender":"MALE",
                                   "phoneNumber":"01579435782"}""")
                        .with(user(users.get(1))))
                .andExpect(status().isOk());
        verify(youthService, times(1)).editYouth(any(YouthDTO.class));
    }

    @Test
    void editYouthWithUnAuthorizedServant() throws Exception {
        when(youthService.editYouth(any(YouthDTO.class))).thenReturn(true);
        mockMvc.perform(patch(EndPoints.YOUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "gender":"MALE",
                                   "phoneNumber":"01579435782"}""")
                        .with(user(users.get(2))))
                .andExpect(status().isForbidden());
        verify(youthService, times(0)).editYouth(any(YouthDTO.class));
    }

    @Test
    @WithAnonymousUser
    void editYouthWithUnAuthenticatedUser() throws Exception {
        mockMvc.perform(post(EndPoints.YOUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "familyId":"1",
                                   "gender":"MALE",
                                   "phoneNumber":"01579435782"}"""))
                .andExpect(status().isUnauthorized());
    }
/////////////// exceptions
    @Test
    void editYouthWithNonHeadServantWithNoFamilyId() throws Exception {
        when(youthService.editYouth(any(YouthDTO.class))).thenReturn(true);
        mockMvc.perform(patch(EndPoints.YOUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(users.get(1)))
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "gender":"MALE",
                                   "phoneNumber":"01579435782"}"""))
                .andExpect(status().isBadRequest());
        verify(youthService, times(0)).editYouth(any(YouthDTO.class));
    }

    @Test
    void editYouthWithHeadServantWithNoFamilyId() throws Exception {
        when(youthService.editYouth(any(YouthDTO.class))).thenReturn(true);
        mockMvc.perform(patch(EndPoints.YOUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(users.get(0)))
                        .content("""
                                {  "firstName":"joseph",
                                   "lastName":"shokry",
                                   "gender":"MALE",
                                   "phoneNumber":"01579435782"}"""))
                .andExpect(status().isBadRequest());
        verify(youthService, times(0)).editYouth(any(YouthDTO.class));
    }

    @Test
    void editYouthWithNonHeadServantWithNoFirstName() throws Exception {
        when(youthService.editYouth(any(YouthDTO.class))).thenReturn(true);
        mockMvc.perform(patch(EndPoints.YOUTH)
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
        when(youthService.editYouth(any(YouthDTO.class))).thenReturn(true);
        mockMvc.perform(patch(EndPoints.YOUTH)
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
        when(youthService.editYouth(any(YouthDTO.class))).thenReturn(true);
        mockMvc.perform(patch(EndPoints.YOUTH)
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
        when(youthService.editYouth(any(YouthDTO.class))).thenReturn(true);
        mockMvc.perform(patch(EndPoints.YOUTH)
                        .with(user(users.get(1))))
                .andExpect(status().isBadRequest());
        verify(youthService, times(0)).editYouth(any(YouthDTO.class));
    }


}
