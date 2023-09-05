package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthIntermediateDTO;
import com.example.demo.models.Family;
import com.example.demo.models.Street;
import com.example.demo.models.Youth;
import com.example.demo.services.FamilyService;
import com.example.demo.services.StreetService;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-05T19:50:21+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class YouthMapperImpl implements YouthMapper {

    @Override
    public Youth youthDtoToYouth(YouthDTO youthDTO, Youth youth, FamilyService familyService, StreetService streetService) {
        if ( youthDTO == null ) {
            return null;
        }

        if ( youthDTO.id != null ) {
            youth.setId( youthDTO.id );
        }
        if ( youthDTO.firstName != null ) {
            youth.setFirstName( youthDTO.firstName );
        }
        if ( youthDTO.lastName != null ) {
            youth.setLastName( youthDTO.lastName );
        }
        if ( youthDTO.phoneNumber != null ) {
            youth.setPhoneNumber( youthDTO.phoneNumber );
        }
        if ( youthDTO.buildingNumber != null ) {
            youth.setBuildingNumber( youthDTO.buildingNumber );
        }
        if ( youthDTO.university != null ) {
            youth.setUniversity( youthDTO.university );
        }
        if ( youthDTO.college != null ) {
            youth.setCollege( youthDTO.college );
        }
        if ( youthDTO.collegeLevel != null ) {
            youth.setCollegeLevel( youthDTO.collegeLevel );
        }
        if ( youthDTO.gradLevel != null ) {
            youth.setGradLevel( youthDTO.gradLevel );
        }
        if ( youthDTO.meetingLevel != null ) {
            youth.setMeetingLevel( youthDTO.meetingLevel );
        }
        if ( youthDTO.notes != null ) {
            youth.setNotes( youthDTO.notes );
        }

        youth.setDayOfBirth( youthDTO.dayOfBirth != null ? java.time.LocalDate.parse(youthDTO.dayOfBirth) : null );

        attachEntities( youthDTO, youth, familyService, streetService );

        return youth;
    }

    @Override
    public YouthDTO youthToYouthDto(Youth youth, YouthDTO youthDTO) {
        if ( youth == null ) {
            return null;
        }

        youthDTO.familyId = youthFamilyId( youth );
        youthDTO.streetId = youthStreetId( youth );
        youthDTO.id = youth.getId();
        youthDTO.firstName = youth.getFirstName();
        youthDTO.lastName = youth.getLastName();
        youthDTO.phoneNumber = youth.getPhoneNumber();
        if ( youth.getDayOfBirth() != null ) {
            youthDTO.dayOfBirth = DateTimeFormatter.ISO_LOCAL_DATE.format( youth.getDayOfBirth() );
        }
        else {
            youthDTO.dayOfBirth = null;
        }
        youthDTO.university = youth.getUniversity();
        youthDTO.college = youth.getCollege();
        youthDTO.collegeLevel = youth.getCollegeLevel();
        youthDTO.gradLevel = youth.getGradLevel();
        youthDTO.meetingLevel = youth.getMeetingLevel();
        youthDTO.notes = youth.getNotes();
        youthDTO.buildingNumber = youth.getBuildingNumber();

        return youthDTO;
    }

    @Override
    public YouthIntermediateDTO youthToYouthIntermediateDto(Youth youth, YouthIntermediateDTO youthIntermediateDTO) {
        if ( youth == null ) {
            return null;
        }

        Integer id = youthFamilyId( youth );
        if ( id != null ) {
            youthIntermediateDTO.familyId = id;
        }
        if ( youth.getId() != null ) {
            youthIntermediateDTO.id = youth.getId();
        }
        if ( youth.getFirstName() != null ) {
            youthIntermediateDTO.firstName = youth.getFirstName();
        }
        if ( youth.getLastName() != null ) {
            youthIntermediateDTO.lastName = youth.getLastName();
        }
        if ( youth.getMeetingLevel() != null ) {
            youthIntermediateDTO.meetingLevel = youth.getMeetingLevel();
        }

        return youthIntermediateDTO;
    }

    private Integer youthFamilyId(Youth youth) {
        if ( youth == null ) {
            return null;
        }
        Family family = youth.getFamily();
        if ( family == null ) {
            return null;
        }
        Integer id = family.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer youthStreetId(Youth youth) {
        if ( youth == null ) {
            return null;
        }
        Street street = youth.getStreet();
        if ( street == null ) {
            return null;
        }
        Integer id = street.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
