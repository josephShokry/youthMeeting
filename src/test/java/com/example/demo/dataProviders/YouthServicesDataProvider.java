package com.example.demo.dataProviders;

import com.example.demo.models.dtos.YouthMidLevelDTO;
import com.example.demo.models.entities.Youth;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class YouthServicesDataProvider {
    @Getter
    private final List<Youth> youthsTable;
    @Getter
    private final List<YouthMidLevelDTO> youthMidLevelDTOTable;

    public YouthServicesDataProvider() {
        this.youthsTable = new ArrayList<>();
        this.youthMidLevelDTOTable = new ArrayList<>();
        prepareData();
    }

    private void prepareData(){
        prepareYouthTable();
        prepareYouthIntermediateDTOTable();
    }

    private void prepareYouthIntermediateDTOTable() {
        youthMidLevelDTOTable.addAll(
                List.of(
                        YouthMidLevelDTO.builder()
                                .id(1L)
                                .firstName("Joseph")
                                .lastName("Shokry")
                                .meetingLevel(3)
                                .familyId(3L)
                                .build(),
                        YouthMidLevelDTO.builder()
                                .id(2L)
                                .firstName("Isaac")
                                .lastName("Vector")
                                .meetingLevel(2)
                                .familyId(2L)
                                .build(),
                        YouthMidLevelDTO.builder()
                                .id(3L)
                                .firstName("Adel")
                                .lastName("Makram")
                                .meetingLevel(1)
                                .familyId(1L)
                                .build(),
                        YouthMidLevelDTO.builder()
                                .id(4L)
                                .firstName("Fady")
                                .lastName("Shokry")
                                .meetingLevel(4)
                                .familyId(4L)
                                .build(),
                        YouthMidLevelDTO.builder()
                                .id(5L)
                                .firstName("Kiro")
                                .lastName("Soliman")
                                .meetingLevel(2)
                                .familyId(2L)
                                .build(),
                        YouthMidLevelDTO.builder()
                                .id(6L)
                                .firstName("Jolie")
                                .lastName("Atef")
                                .meetingLevel(3)
                                .familyId(3L)
                                .build()
                )
        );

    }


    private void prepareYouthTable(){
        youthsTable.addAll(
                List.of(
                        Youth.builder()
                                .firstName("Joseph")
                                .lastName("Shokry")
                                .dayOfBirth(LocalDate.parse("2002-04-09"))
                                .phoneNumber("01284024832")
                                .build(),
                        Youth.builder()
                                .firstName("Isaac")
                                .lastName("Vector")
                                .dayOfBirth(LocalDate.parse("2003-09-04"))
                                .phoneNumber("01278497512")
                                .build(),
                        Youth.builder()
                                .firstName("Adel")
                                .lastName("Makram")
                                .dayOfBirth(LocalDate.parse("1998-10-14"))
                                .phoneNumber("01579486321")
                                .build(),
                        Youth.builder()
                                .firstName("Fady")
                                .lastName("Shokry")
                                .dayOfBirth(LocalDate.parse("2003-05-04"))
                                .phoneNumber("01147547894")
                                .build(),
                        Youth.builder()
                                .firstName("Kiro")
                                .lastName("Soliman")
                                .dayOfBirth(LocalDate.parse("2004-11-22"))
                                .phoneNumber("01075471369")
                                .build(),
                        Youth.builder()
                                .firstName("Josephine")
                                .lastName("Atef")
                                .dayOfBirth(LocalDate.parse("2001-01-30"))
                                .phoneNumber("01578945617")
                                .build()
                )
        );
    }

}
