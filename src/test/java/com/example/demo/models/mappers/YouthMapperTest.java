package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.entities.Family;
import com.example.demo.models.entities.Street;
import com.example.demo.models.entities.Youth;
import com.example.demo.services.implementations.FamilyService;
import com.example.demo.services.implementations.StreetService;
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
    @MockBean private FamilyService familyServices;
    @MockBean private StreetService streetServices;



    @Test
    void TestYouthDtoToYouth() {
        YouthDTO youthDTO = new YouthDTO(1L, "Joseph", "Shokry", "01284024832",
                "2002-09-04", "Alex", "eng", "3", 5, 3,
                "good person", 1L, 1L, 16);

        when(familyServices.findFamilyById(1L)).thenReturn(new Family(1L,"mark",3, 2020));
        when(streetServices.findById(1L)).thenReturn(new Street(1L,"ishaky", null));

        Youth targetYouth = youthMapper.youthDtoToYouth(youthDTO, new Youth(),familyServices, streetServices);

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
        verify(familyServices).findFamilyById(1L);
        verify(streetServices).findById(1L);
        assertThat(targetYouth.getFamily()).isNotNull();
        assertThat(targetYouth.getStreet()).isNotNull();
        assertThat(targetYouth.getFamily().getName()).isEqualTo("mark");
        assertThat(targetYouth.getStreet().getName()).isEqualTo("ishaky");
    }


    @Test
    void youthToYouthDto() {
        Family family = new Family(1L,"mark",3,2020);
        Street street = new Street(1L,"ishaky", null);
        Youth youth = Youth.builder()
                .id(1L)
                .firstName("Joseph")
                .lastName("Shokry")
                .dayOfBirth(LocalDate.parse("2002-09-04"))
                .phoneNumber("01284024832")
                .buildingNumber(16)
                .street(street)
                .university("Alex")
                .college("eng")
                .family(family)
                .notes("good person")
                .collegeLevel("3")
                .gradLevel(5)
                .meetingLevel(3)
                .build();
//        Youth youth = new Youth("Alex","eng",3,5,family,3, null,"good person");
//        youth.setId(1L);
//        youth.setFirstName("Joseph");
//        youth.setLastName("Shokry");
//        youth.setDayOfBirth(LocalDate.parse("2002-09-04"));
//        youth.setPhoneNumber("01284024832");
//        youth.setBuildingNumber(16);
//        youth.setStreet(street);

        YouthDTO targetYouthDTO = youthMapper.youthToYouthDto(youth,new YouthDTO());
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
    }
}