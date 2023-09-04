package com.example.demo.dataProviders;

import com.example.demo.models.DTOs.YouthIntermediateDTO;
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
    private final List<YouthIntermediateDTO> youthIntermediateDTOTable;

    public YouthServicesDataProvider() {
        this.youthsTable = new ArrayList<>();
        this.youthIntermediateDTOTable = new ArrayList<>();
        prepareData();
    }

    private void prepareData(){
        prepareYouthTable();
        prepareYouthIntermediateDTOTable();
    }

    private void prepareYouthIntermediateDTOTable() {
        youthIntermediateDTOTable.addAll(
                List.of(
                        YouthIntermediateDTO.builder()
                                .id(1)
                                .firstName("Joseph")
                                .lastName("Shokry")
                                .meetingLevel(3)
                                .familyId(3)
                                .build(),
                        YouthIntermediateDTO.builder()
                                .id(2)
                                .firstName("Isaac")
                                .lastName("Vector")
                                .meetingLevel(2)
                                .familyId(2)
                                .build(),
                        YouthIntermediateDTO.builder()
                                .id(3)
                                .firstName("Adel")
                                .lastName("Makram")
                                .meetingLevel(1)
                                .familyId(1)
                                .build(),
                        YouthIntermediateDTO.builder()
                                .id(4)
                                .firstName("Fady")
                                .lastName("Shokry")
                                .meetingLevel(4)
                                .familyId(4)
                                .build(),
                        YouthIntermediateDTO.builder()
                                .id(5)
                                .firstName("Kiro")
                                .lastName("Soliman")
                                .meetingLevel(2)
                                .familyId(2)
                                .build(),
                        YouthIntermediateDTO.builder()
                                .id(6)
                                .firstName("Jolie")
                                .lastName("Atef")
                                .meetingLevel(3)
                                .familyId(3)
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
