package com.example.demo.repositories;

import com.example.demo.dataProviders.YouthRepositoryDataProvider;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.entities.Area;
import com.example.demo.models.entities.Family;
import com.example.demo.models.entities.Street;
import com.example.demo.models.entities.Youth;
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
    private final List<Youth> youthsTable;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private YouthRepository youthRepository;

    public YouthRepositoryTest(@Autowired final YouthRepositoryDataProvider youthRepositoryDataProvider) {
        youthsTable = youthRepositoryDataProvider.getYouthsTable();
    }

    @BeforeAll
    static void setup(@Autowired DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("databasePreparation/YouthRepositoryTestDataBase.sql"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Assertion methods to check equality of objects depending on the values instead of the reference
    private void assertFamilyEquals(Family expected, Family actual) {
        assertThat(actual.getFamilyName()).isEqualTo(expected.getFamilyName());
        assertThat(actual.getFamilyLevel()).isEqualTo(expected.getFamilyLevel());
        assertThat(actual.getJoiningYear()).isEqualTo(expected.getJoiningYear());
    }

    private void assertStreetEquals(Street expected, Street actual) {
        assertThat(actual.getStreetName()).isEqualTo(expected.getStreetName());
        assertAreaEquals(expected.getArea(), actual.getArea());
    }

    private void assertAreaEquals(Area expected, Area actual) {
        assertThat(actual.getAreaName()).isEqualTo(expected.getAreaName());
    }

    private void assertYouthEquals(Youth expected, Youth actual) {
        assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
        assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
        assertFamilyEquals(expected.getFamily(), actual.getFamily());
        assertStreetEquals(expected.getStreet(), actual.getStreet());
    }

    private void assertYouthsEquals(List<Youth> expected, List<Youth> actual) {
        assertThat(expected.size()).isEqualTo(actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertYouthEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(youthRepository).isNotNull();
    }

    @Test
    void findAllWithNoFiltersAndDefaultPagination() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO();
        Page<Youth> actualYouthPage = new PageImpl<Youth>(youthsTable);
        Pageable paging = PageRequest.of(0, 10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }

    @Test
    void findAllWithCustomPagination() {
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().size(2).page(1).build();
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(2), youthsTable.get(3)));
        Pageable paging = PageRequest.of(1,2);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }

    @Test
    void findAllWithNamePartFilterAndDefaultPagination() {
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().namePart("okr").build();
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(0), youthsTable.get(3)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @Test
    void findAllWithNamePartAndMonthFilterAndDefaultPagination() {
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().namePart("se").month(4).build();
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(0)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @Test
    void findAllWithYear() {
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().year(1998).build();
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(2)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @Test
    void findAllWithMonthAndYear() {
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().month(9).year(2003).build();
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(1)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @Test
    void findAllWithFamily() {
        int familyIdForSearch = 1;
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().familyId(familyIdForSearch).build();
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(0), youthsTable.get(2), youthsTable.get(4)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @Test
    void findAllWithStreet() {
        int streetIdForSearch = 2;
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().streetId(streetIdForSearch).build();
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(1), youthsTable.get(3), youthsTable.get(5)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }

    @Test
    void findAllWithNamePartAndStreet() {
        int streetIdForSearch = 2;
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().streetId(streetIdForSearch).namePart("aa").build();
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(1)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
}