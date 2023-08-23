package com.example.demo.repositories;

import com.example.demo.models.Area;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.Family;
import com.example.demo.models.Street;
import com.example.demo.models.Youth;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class YouthRepositoryTest {
    @Autowired private EntityManager entityManager;
    @Autowired private YouthRepository youthRepository;
    List<Youth> youthsTable = List.of(
                new Youth(null, "Joseph",  "Shokry","2002-04-09","01284024832"),
                new Youth(null, "Isaac",  "Vector","2003-09-04","01278497512"),
                new Youth(null, "Adel", "Makram","1998-10-14","01579486321"),
                new Youth(null, "Fady", "Shokry","2003-05-04","01147547894"),
                new Youth(null, "Kiro", "Soliman","2004-11-22","01075471369"),
                new Youth(null, "Josephine", "Atef","2001-01-30","01578945617"));
    List<Family> familiesTable = List.of(
            new Family(null,"Mark",3,null,null,2021),
            new Family(null,"John",1,null,null,2023));
    List<Street> streetsTable = List.of(
        new Street(null,"Ishaky",null, null),
        new Street(null,"شجره الدر",null, null));
    List<Area> areasTable = List.of(
        new Area(null,"Moharm bek", null),
        new Area(null,"غربال", null));

    @BeforeAll
    static void setup(@Autowired DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("databasePreparation/YouthRepositoryTestDataBase.sql"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(youthRepository).isNotNull();
    }
    private void assertYouthEquals(Youth expected, Youth actual) {
        assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
        assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
    }
    private void assertYouthsEquals(List<Youth> expected, List<Youth> actual){
        assertThat(expected.size()).isEqualTo(actual.size());
        for(int i=0;i< expected.size();i++){
            assertYouthEquals(expected.get(i), actual.get(i));
        }
    }
    @Test
    void findAllWithNoFiltersAndDefaultPagination() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,null,null,null,null,null,null);
        Page<Youth> actualYouthPage = new PageImpl<Youth>(youthsTable);
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification,paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @Test
    void findAllWithCustomPagination() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,null,null,null,null,2,1);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(2), youthsTable.get(3)));
        Pageable paging = PageRequest.of(1,2);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }

    @Test
    void findAllWithNamePartFilterAndDefaultPagination() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,null,"okr",null,null,null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(0), youthsTable.get(3)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @Test
    void findAllWithNamePartAndMonthFilterAndDefaultPagination() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,null,"se",4,null,null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(0)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @Test
    void findAllWithYear() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,null,null,null,1998,null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(2)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @Test
    void findAllWithMonthAndYear() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,null,null,9,2003,null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(1)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @Test
    void findAllWithFamily() {
        int familyIdForSearch = 1;
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(familyIdForSearch,null,null,null,null,null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(0), youthsTable.get(2), youthsTable.get(4)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @Test
    void findAllWithStreet() {
        int streetIdForSearch = 2;
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,streetIdForSearch,null,null,null,null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(1), youthsTable.get(3), youthsTable.get(5)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }

    @Test
    void findAllWithNamePartAndStreet() {
        int streetIdForSearch = 2;
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,streetIdForSearch,"aa",null,null,null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(1)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
}