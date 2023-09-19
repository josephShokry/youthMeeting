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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if(assertNull(expected, actual))return;
        assertThat(actual.getFamilyName()).isEqualTo(expected.getFamilyName());
        assertThat(actual.getFamilyLevel()).isEqualTo(expected.getFamilyLevel());
        assertThat(actual.getJoiningYear()).isEqualTo(expected.getJoiningYear());
    }

    private void assertStreetEquals(Street expected, Street actual) {
        if(assertNull(expected, actual))return;
        assertThat(actual.getStreetName()).isEqualTo(expected.getStreetName());
        assertAreaEquals(expected.getArea(), actual.getArea());
    }

    private void assertAreaEquals(Area expected, Area actual) {
        if(assertNull(expected, actual))return;
        assertThat(actual.getAreaName()).isEqualTo(expected.getAreaName());
    }

    private boolean assertNull(Object expected, Object actual){
        if(expected == null && actual == null)return true;
        assert actual != null;
        assert expected != null;
        return false;
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
    List<Youth> getListOfConsecutiveYouths(int firstYouth, int sizeOfList){
        List<Youth> expectedList = new ArrayList<>();
        for(int i = firstYouth; i < firstYouth + sizeOfList; i++)
            expectedList.add(youthsTable.get(i));
        return expectedList;
    }
    List<Youth> getListOfYouths(String inds){
        return Optional.ofNullable(inds)
                .map(str -> Arrays.stream(str.split(" "))
                        .filter(s -> !s.isEmpty())
                        .map(Integer::parseInt)
                        .map(youthsTable::get)
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
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

    @ParameterizedTest
    @CsvSource({
            "2, 1, 2, 2",
            "7, 1, 0, 0",
            "10, 0, 0, 6",
            "6, 0, 0, 6",
            "3, 2, 0, 0",
            "3, 1, 3, 3",
            "3, 0, 0, 3",
            "1, 5, 5, 1",
            "1, 30, 0, 0"
    })
    void findAllWithCustomPagination(int pageSize, int pageNumber, int firstYouthIndex, int YouthListSize) {
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().size(pageSize).page(pageNumber).build();
        Page<Youth> actualYouthPage = new PageImpl<>(getListOfConsecutiveYouths(firstYouthIndex, YouthListSize));
        Pageable paging = PageRequest.of(pageNumber,pageSize);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }

    @ParameterizedTest
    @CsvSource({
            "okr, 0 3",
            "l M, 2",
            "Jo, 0 5",
            "S, 0 3 4",
            "s, 0 1 5",
            "Joseph, 0 5",
            "Shokry, 0 3"
    })
    void findAllWithNamePartFilterAndDefaultPagination(String namePart, String expectedIndexes) {
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().namePart(namePart).build();
        Page<Youth> actualYouthPage = new PageImpl<>(getListOfYouths(expectedIndexes));//List.of(youthsTable.get(0), youthsTable.get(3)));
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
        Long familyIdForSearch = 1L;
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().familyId(familyIdForSearch).build();
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(0), youthsTable.get(2), youthsTable.get(4)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @Test
    void findAllWithStreet() {
        Long streetIdForSearch = 2L;
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().streetId(streetIdForSearch).build();
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(1), youthsTable.get(3), youthsTable.get(5)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }

    @Test
    void findAllWithNamePartAndStreet() {
        Long streetIdForSearch = 2L;
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().streetId(streetIdForSearch).namePart("aa").build();
        Page<Youth> actualYouthPage = new PageImpl<>(List.of(youthsTable.get(1)));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
    @ParameterizedTest
    @CsvSource({
            " , , , , , 0 1 2 3 4 5",
            " , 2002, , , , 0",
            " , 2001, , , , 5",
            " , 2002, 4, , , 0",
            " , 2002, 5, , , ",
            " , , , 1, , 0 2 4",
            " , , , 2, , 1 3 5",
            " , , , 1, 2, ",
            " , , , 1, 1, 0 2 4",
            "Joseph, , , , , 0 5",
            "Joseph, , , 1, , 0",
    })
    void findAllWithFiltering(String namePart, Integer year, Integer month, Long familyId, Long streetId, String expectedIndexes){
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().namePart(namePart).year(year).month(month)
                .familyId(familyId).streetId(streetId).build();
        Page<Youth> actualYouthPage = new PageImpl<>(getListOfYouths(expectedIndexes));
        Pageable paging = PageRequest.of(0,10);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> result = youthRepository.findAll(specification, paging);
        assertYouthsEquals(result.getContent(), actualYouthPage.getContent());
    }
}
