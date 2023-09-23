package com.example.demo.dataProviders;

import com.example.demo.models.entities.*;
import com.example.demo.models.enums.Roles;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class YouthControllerDataProvider {
    @Getter
    private final List<Family> familiesTable;
    private final List<Person> personsTable;
    @Getter
    private final List<User> usersTable;

    public YouthControllerDataProvider() {
        this.familiesTable = new ArrayList<>();
        this.personsTable = new ArrayList<>();
        this.usersTable = new ArrayList<>();
        prepareData();
    }

    private void prepareData(){
        prepareFamiliesTable();
        preparePersonsTable();
        prepareUsersTable();
    }

    private void prepareFamiliesTable() {
        familiesTable.addAll(
                List.of(
                        Family.builder()
                                .id(1L)
                                .name("mark")
                                .build(),
                        Family.builder()
                                .id(2L)
                                .name("john")
                                .build()
                )
        );
    }
    private void preparePersonsTable() {
        Family fam = new Family();
        personsTable.addAll(
                List.of(
                        Servant.builder()
                                .family(familiesTable.get(0))
                                .build(),
                        Servant.builder()
                                .family(familiesTable.get(1))
                                .build()
                )
        );
    }
    private void prepareUsersTable() {
        usersTable.addAll(
                List.of(
                        User.builder()
                                .username("head")
                                .password("pas")
                                .enabled(true)
                                .role(Roles.ROLE_SERVANT_HEAD)
                                .authenticated(true)
                                .build(),
                        User.builder()
                                .username("servant 1")
                                .password("pas")
                                .enabled(true)
                                .role(Roles.ROLE_SERVANT)
                                .person(personsTable.get(0))
                                .authenticated(true)
                                .build(),
                        User.builder()
                                .username("servant 2")
                                .password("pas")
                                .enabled(true)
                                .role(Roles.ROLE_SERVANT)
                                .person(personsTable.get(1))
                                .authenticated(true)
                                .build()
                )
        );
    }
}
