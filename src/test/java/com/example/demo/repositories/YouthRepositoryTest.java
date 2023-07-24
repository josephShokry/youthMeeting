package com.example.demo.repositories;

import com.example.demo.models.Area;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.Family;
import com.example.demo.models.Street;
import com.example.demo.models.Youth;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class YouthRepositoryTest {
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
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
            new Family(null,"Mark",3,null,2021),
            new Family(null,"John",1,null,2023));
    List<Street> streetsTable = List.of(
        new Street(null,"Ishaky",null),
        new Street(null,"شجره الدر",null));
    List<Area> areasTable = List.of(
        new Area(null,"Moharm bek",null),
        new Area(null,"غربال",null));

    @BeforeEach
    void setUp() {
        int counter = 0;
        for(Youth youth: youthsTable){
            int id = counter%2;
                youth.setFamily(familiesTable.get(id));
                youth.setStreet(streetsTable.get(id));
                youth.setArea(areasTable.get(id));
            youthRepository.save(youth);
            counter++;
        }
        entityManager.flush();
    }

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(youthRepository).isNotNull();
    }

    @Test
    void findAllWithNoFiltersAndDefaultPagination() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,null,null,null,null,null,null);
        Page<Youth> actualYouthPage = new PageImpl<Youth>(youthsTable);
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification,paging);
        assertThat(result.getContent()).isEqualTo(actualYouthPage.getContent());
    }
    @Test
    void findAllWithCustomPagination() {
        // TODO make a test for checking the assignment of page and size from the filtersDTO to the page object that pass to the repo
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,null,null,null,null,2,1);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(2), youthsTable.get(3)));
        Pageable paging = PageRequest.of(1,2);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertThat(result.getContent()).isEqualTo(actualYouthPage.getContent());
    }

    @Test
    void findAllWithNamePartFilterAndDefaultPagination() {
        // TODO don't know why the 2 char from the last name doesn't work
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,null,"se",null,null,null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(0), youthsTable.get(5)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertThat(result.getContent()).isEqualTo(actualYouthPage.getContent());
    }
    @Test
    void findAllWithNamePartAndMonthFilterAndDefaultPagination() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,null,"se",4,null,null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(0)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertThat(result.getContent()).isEqualTo(actualYouthPage.getContent());
    }
    @Test
    void findAllWithDOB() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,null,null,null,"1998-10-14",null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(2)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertThat(result.getContent()).isEqualTo(actualYouthPage.getContent());
    }
    @Test
    @Disabled
    void findAllWithFamily() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(1,null,null,null,null,null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(0), youthsTable.get(2), youthsTable.get(4)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertThat(result.getContent()).isEqualTo(actualYouthPage.getContent());
    }
    @Test
    @Disabled
    void findAllWithStreet() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,2,null,null,null,null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(1), youthsTable.get(3), youthsTable.get(5)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertThat(result.getContent()).isEqualTo(actualYouthPage.getContent());
    }

    @Test
    @Disabled
    void findAllWithNamePartAndStreet() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,2,"aa",null,null,null,null);
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(1)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertThat(result.getContent()).isEqualTo(actualYouthPage.getContent());
    }
}