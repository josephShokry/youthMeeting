package com.example.demo.models.mappers;

import com.example.demo.models.dtos.YouthDTO;
import com.example.demo.models.entities.Family;
import com.example.demo.models.entities.Street;
import com.example.demo.models.entities.Youth;
import com.example.demo.services.implementations.FamilyService;
import com.example.demo.services.implementations.FatherService;
import com.example.demo.services.implementations.StreetService;
import com.example.demo.models.enums.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class YouthMapperTest {
        // TODO: add test for checking the father in the mapper of the youth
        // i added all the youths to father id = 1 and didn't attach any father
         @InjectMocks
    private YouthMapper youthMapper = Mappers.getMapper(YouthMapper.class);
    @MockBean private FamilyService familyService;
    @MockBean private StreetService streetService;
    @MockBean private FatherService fatherService;



    @Test
    void TestYouthDtoToYouth() {
        YouthDTO youthDTO = YouthDTO.builder()
                .id(1L).firstName("Joseph").lastName("Shokry")
                .dayOfBirth("2002-09-04")
                .phoneNumber("01284024832").buildingNumber(16)
                .streetId(1L).university("Alex").college("eng")
                .familyId(1L).notes("good person").collegeLevel("3")
                .gradLevel(5).meetingLevel(3).gender(Gender.FEMALE)
                .build();

        when(familyService.findFamilyById(1L)).thenReturn(Family.builder().id(1L).name("mark")
                .joiningYear(2020).familyLevel(3).build());
        when(streetService.findById(1L)).thenReturn(Street.builder().id(1L).name("ishaky").build());
        Youth targetYouth = youthMapper.mapYouthDTO(youthDTO, new Youth(),familyService, streetService, fatherService);

        assertThat(targetYouth.getFirstName()).isEqualTo("Joseph");
        assertThat(targetYouth.getLastName()).isEqualTo("Shokry");
        assertThat(targetYouth.getPhoneNumber()).isEqualTo("01284024832");
        assertThat(targetYouth.getDayOfBirth()).isEqualTo(LocalDate.parse("2002-09-04"));
        assertThat(targetYouth.getUniversity()).isEqualTo("Alex");
        assertThat(targetYouth.getCollege()).isEqualTo("eng");
        assertThat(targetYouth.getCollegeLevel()).isEqualTo("3");
        assertThat(targetYouth.getGradLevel()).isEqualTo(5);
        assertThat(targetYouth.getMeetingLevel()).isEqualTo(3);
        assertThat(targetYouth.getNotes()).isEqualTo("good person");
        assertThat(targetYouth.getBuildingNumber()).isEqualTo(16);
        verify(familyService).findFamilyById(1L);
        verify(streetService).findById(1L);
        assertThat(targetYouth.getFamily()).isNotNull();
        assertThat(targetYouth.getStreet()).isNotNull();
        assertThat(targetYouth.getFamily().getName()).isEqualTo("mark");
        assertThat(targetYouth.getStreet().getName()).isEqualTo("ishaky");
        assertThat(targetYouth.getGender()).isEqualTo(Gender.FEMALE);
    }


    @Test
    void youthToYouthDto() {
        Family family = Family.builder().id(1L).name("mark").joiningYear(2020).familyLevel(3).build();
        Street street = Street.builder().id(1L).name("ishaky").build();
        Youth youth = Youth.builder()
                .id(1L).firstName("Joseph").lastName("Shokry")
                .dayOfBirth(LocalDate.parse("2002-09-04"))
                .phoneNumber("01284024832").buildingNumber(16)
                .street(street).university("Alex").college("eng")
                .family(family).notes("good person").collegeLevel("3")
                .gradLevel(5).meetingLevel(3).gender(Gender.MALE)
                .build();
        YouthDTO targetYouthDTO = youthMapper.mapYouth(youth,new YouthDTO());
        assertThat(targetYouthDTO.getFirstName()).isEqualTo("Joseph");
        assertThat(targetYouthDTO.getLastName()).isEqualTo("Shokry");
        assertThat(targetYouthDTO.getPhoneNumber()).isEqualTo("01284024832");
        assertThat(targetYouthDTO.getDayOfBirth()).isEqualTo("2002-09-04");
        assertThat(targetYouthDTO.getUniversity()).isEqualTo("Alex");
        assertThat(targetYouthDTO.getCollege()).isEqualTo("eng");
        assertThat(targetYouthDTO.getCollegeLevel()).isEqualTo("3");
        assertThat(targetYouthDTO.getGradLevel()).isEqualTo(5);
        assertThat(targetYouthDTO.getMeetingLevel()).isEqualTo(3);
        assertThat(targetYouthDTO.getNotes()).isEqualTo("good person");
        assertThat(targetYouthDTO.getBuildingNumber()).isEqualTo(16);
        assertThat(targetYouthDTO.getFamilyId()).isEqualTo(1);
        assertThat(targetYouthDTO.getStreetId()).isEqualTo(1);
        assertThat(targetYouthDTO.getGender()).isEqualTo(Gender.MALE);
    }
}