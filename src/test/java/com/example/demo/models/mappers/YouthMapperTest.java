package com.example.demo.models.mappers;

import com.example.demo.models.Area;
import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.Family;
import com.example.demo.models.Street;
import com.example.demo.models.Youth;
import com.example.demo.services.AreaServices;
import com.example.demo.services.FamilyServices;
import com.example.demo.services.StreetServices;
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
    @InjectMocks
    private YouthMapper youthMapper = Mappers.getMapper(YouthMapper.class);
    @MockBean private FamilyServices familyServices;
    @MockBean private StreetServices streetServices;


    @Test
    void TestYouthDtoToYouth() {
        YouthDTO youthDTO = new YouthDTO(1, "Joseph", "Shokry", "01284024832",
                "2002-09-04", "Alex", "eng", 3, 5, 3,
                "good person", 1, 1, 16);

        when(familyServices.getFamilyById(1)).thenReturn(new Family(1,"mark",3,null,null,2020));
        when(streetServices.getById(1)).thenReturn(new Street(1,"ishaky", null, null));

        Youth targetYouth = youthMapper.youthDtoToYouth(youthDTO, new Youth(),familyServices, streetServices);

        assertThat(targetYouth.getFirstName()).isEqualTo("Joseph");
        assertThat(targetYouth.getLastName()).isEqualTo("Shokry");
        assertThat(targetYouth.getPhoneNumber()).isEqualTo("01284024832");
        assertThat(targetYouth.getDayOfBirth()).isEqualTo(LocalDate.parse("2002-09-04"));
        assertThat(targetYouth.getUniversity()).isEqualTo("Alex");
        assertThat(targetYouth.getCollege()).isEqualTo("eng");
        assertThat(targetYouth.getCollegeLevel()).isEqualTo(3);
        assertThat(targetYouth.getGradLevel()).isEqualTo(5);
        assertThat(targetYouth.getMeetingLevel()).isEqualTo(3);
        assertThat(targetYouth.getNotes()).isEqualTo("good person");
        assertThat(targetYouth.getBuildingNumber()).isEqualTo(16);
        verify(familyServices).getFamilyById(1);
        verify(streetServices).getById(1);
        assertThat(targetYouth.getFamily()).isNotNull();
        assertThat(targetYouth.getStreet()).isNotNull();
        assertThat(targetYouth.getFamily().getFamilyName()).isEqualTo("mark");
        assertThat(targetYouth.getStreet().getStreetName()).isEqualTo("ishaky");
    }


    @Test
    void youthToYouthDto() {
        Family family = new Family(1,"mark",3,null,null,2020);
        Street street = new Street(1,"ishaky",null, null);
        Youth youth = new Youth("Alex","eng",3,5,family,3,"good person");
        youth.setId(1);
        youth.setFirstName("Joseph");
        youth.setLastName("Shokry");
        youth.setDayOfBirth(LocalDate.parse("2002-09-04"));
        youth.setPhoneNumber("01284024832");
        youth.setBuildingNumber(16);
        youth.setStreet(street);
        YouthDTO targetYouthDTO = youthMapper.youthToYouthDto(youth,new YouthDTO());
        assertThat(targetYouthDTO.firstName).isEqualTo("Joseph");
        assertThat(targetYouthDTO.lastName).isEqualTo("Shokry");
        assertThat(targetYouthDTO.phoneNumber).isEqualTo("01284024832");
        assertThat(targetYouthDTO.dayOfBirth).isEqualTo("2002-09-04");
        assertThat(targetYouthDTO.university).isEqualTo("Alex");
        assertThat(targetYouthDTO.college).isEqualTo("eng");
        assertThat(targetYouthDTO.collegeLevel).isEqualTo(3);
        assertThat(targetYouthDTO.gradLevel).isEqualTo(5);
        assertThat(targetYouthDTO.meetingLevel).isEqualTo(3);
        assertThat(targetYouthDTO.notes).isEqualTo("good person");
        assertThat(targetYouthDTO.buildingNumber).isEqualTo(16);
        assertThat(targetYouthDTO.familyId).isEqualTo(1);
        assertThat(targetYouthDTO.streetId).isEqualTo(1);
    }
}