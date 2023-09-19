package com.example.demo.dataProviders;

import com.example.demo.models.entities.*;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class YouthRepositoryDataProvider {
    @Getter
    private final List<Youth> youthsTable;
    private final List<Family> familiesTable;
    private final List<Street> streetsTable;
    private final List<Area> areasTable;

    public YouthRepositoryDataProvider() {
        this.youthsTable = new ArrayList<>();
        this.familiesTable = new ArrayList<>();
        this.streetsTable = new ArrayList<>();
        this.areasTable = new ArrayList<>();
        prepareData();
    }

    private void prepareData(){
        prepareAreaTable();
        prepareStreetTable();
        prepareFamilyTable();
        prepareYouthTable();
    }
    private void prepareAreaTable(){
        areasTable.addAll(
                List.of(
                        Area.builder()
                                .areaName("Moharm bek")
                                .build(),
                        Area.builder()
                                .areaName("bab geded")
                                .build()
                )
        );
    }
    private void prepareStreetTable(){
        streetsTable.addAll(
                List.of(
                        Street.builder()
                                .streetName("Ishaky")
                                .area(areasTable.get(0))
                                .build(),
                        Street.builder()
                                .streetName("thoryia")
                                .area(areasTable.get(1))
                                .build()
                )
        );
    }
    private void prepareFamilyTable(){
        familiesTable.addAll(
                List.of(
                        Family.builder()
                                .familyName("Mark")
                                .familyLevel(3)
                                .joiningYear(2021)
                                .build(),
                        Family.builder()
                                .familyName("John")
                                .familyLevel(1)
                                .joiningYear(2023)
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
                                .family(familiesTable.get(0))
                                .street(streetsTable.get(0))
                                .build(),
                        Youth.builder()
                                .firstName("Isaac")
                                .lastName("Vector")
                                .dayOfBirth(LocalDate.parse("2003-09-04"))
                                .phoneNumber("01278497512")
                                .family(familiesTable.get(1))
                                .street(streetsTable.get(1))
                                .build(),
                        Youth.builder()
                                .firstName("Adel")
                                .lastName("Makram")
                                .dayOfBirth(LocalDate.parse("1998-10-14"))
                                .phoneNumber("01579486321")
                                .family(familiesTable.get(0))
                                .street(streetsTable.get(0))
                                .build(),
                        Youth.builder()
                                .firstName("Fady")
                                .lastName("Shokry")
                                .dayOfBirth(LocalDate.parse("2003-05-04"))
                                .phoneNumber("01147547894")
                                .family(familiesTable.get(1))
                                .street(streetsTable.get(1))
                                .build(),
                        Youth.builder()
                                .firstName("Kiro")
                                .lastName("Soliman")
                                .dayOfBirth(LocalDate.parse("2004-11-22"))
                                .phoneNumber("01075471369")
                                .family(familiesTable.get(0))
                                .street(streetsTable.get(0))
                                .build(),
                        Youth.builder()
                                .firstName("Josephine")
                                .lastName("Atef")
                                .dayOfBirth(LocalDate.parse("2001-01-30"))
                                .phoneNumber("01578945617")
                                .family(familiesTable.get(1))
                                .street(streetsTable.get(1))
                                .build()
                )
        );
    }
}
