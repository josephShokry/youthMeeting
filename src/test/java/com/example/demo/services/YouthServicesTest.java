package com.example.demo.services;

import com.example.demo.dataProviders.YouthServicesDataProvider;
import com.example.demo.exceptions.exceptions.DataNotFoundException;
import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.DTOs.YouthMidLevelDTO;
import com.example.demo.models.entities.Family;
import com.example.demo.models.entities.Youth;
import com.example.demo.models.mappers.YouthMapper;
import com.example.demo.repositories.YouthRepository;
import com.example.demo.services.implementations.AreaService;
import com.example.demo.services.implementations.FamilyService;
import com.example.demo.services.implementations.StreetService;
import com.example.demo.services.implementations.YouthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class YouthServicesTest {
    // TODO: add test for father in the youth
    @InjectMocks
    private YouthService youthService;
    @MockBean private YouthRepository youthRepository;
    @MockBean private FamilyService familyService;
    @MockBean private AreaService areaService;
    @MockBean private StreetService streetService;
    @MockBean private YouthMapper youthMapper;
    private final YouthDTO emptyYouthDto = new YouthDTO();
    private final List<Youth> youthsTable;
    private final List<YouthMidLevelDTO> youthMidLevelDTOTable;

    public YouthServicesTest(@Autowired final YouthServicesDataProvider youthServicesDataProvider) {
        youthsTable = youthServicesDataProvider.getYouthsTable();
        youthMidLevelDTOTable = youthServicesDataProvider.getYouthMidLevelDTOTable();
    }

    @Test
    void testAddYouth() {
        when(youthMapper.youthDtoToYouth(eq(emptyYouthDto),any(Youth.class),any(FamilyService.class),
                any(StreetService.class))).thenReturn(youthsTable.get(0));
        youthService.addYouth(emptyYouthDto);
        verify(youthMapper, times(1)).youthDtoToYouth(eq(emptyYouthDto),any(Youth.class)
                ,any(FamilyService.class), any(StreetService.class));
        verify(youthRepository, times(1)).save(youthsTable.get(0));
    }
    @Test
    void testGetYouthByCorrectId() {
        when(youthRepository.findById(1L)).thenReturn(Optional.of(youthsTable.get(0)));
        assertThat(youthService.findYouthById(1L)).isEqualTo(youthsTable.get(0));
        verify(youthRepository,times(1)).findById(1L);
    }

    @Test
    void testGetYouthByIdWithWrongId() {
        when(youthRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> youthService.findYouthById(1L))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage("the required youth is not present");
        verify(youthRepository).findById(1L);
    }

    @Test
    void testGetYouthDtoByCorrectId() {
        YouthDTO fullYouthDto = YouthDTO.builder()
                .id(1L).firstName("Joseph").lastName("Shokry").phoneNumber("01284024832")
                .build();
        when(youthRepository.findById(1L)).thenReturn(Optional.of(youthsTable.get(0)));
        when(youthMapper.youthToYouthDto(eq(youthsTable.get(0)), any(YouthDTO.class))).thenReturn(fullYouthDto);
        assertThat(youthService.findYouthDtoById(1L)).isEqualTo(fullYouthDto);
        verify(youthRepository,times(1)).findById(1L);
        verify(youthMapper,times(1)).youthToYouthDto(eq(youthsTable.get(0)),any(YouthDTO.class));
    }
    @Test
    void testGetYouthDtoByIdWithWrongId() {
        when(youthRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> youthService.findYouthDtoById(1L))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage("the required youth is not present");
        verify(youthRepository).findById(1L);
        verify(youthMapper, times(0)).youthToYouthDto(any(Youth.class),any(YouthDTO.class));
    }

    @Test
    void testEditYouth() {
        Youth fullYouth = youthsTable.get(0);
        emptyYouthDto.setId(1L);
        fullYouth.setFirstName("isaak");
        fullYouth.setLastName("vector");
        Family family = Family.builder().id(1L).name("mark").family_level(3).joiningYear(2021).build();
        fullYouth.setFamily(family);
        when(youthRepository.findById(emptyYouthDto.getId())).thenReturn(Optional.of(youthsTable.get(0)));
        when(youthMapper.youthDtoToYouth(eq(emptyYouthDto),eq(youthsTable.get(0)),any(FamilyService.class),
                any(StreetService.class))).thenReturn(fullYouth);
        youthService.editYouth(emptyYouthDto);
        verify(youthMapper, times(1)).youthDtoToYouth(eq(emptyYouthDto),any(Youth.class)
                ,any(FamilyService.class), any(StreetService.class));
        verify(youthRepository, times(1)).save(fullYouth);
        verify(youthRepository,times(1)).findById(emptyYouthDto.getId());
    }

    @Test
    void testEditYouthWithWrongId() {
        Youth fullYouth = youthsTable.get(0);
        emptyYouthDto.setId(100L);
        fullYouth.setFirstName("isaak");
        fullYouth.setLastName("vector");
        Family family = Family.builder().id(1L).name("mark").family_level(3).joiningYear(2021).build();
        fullYouth.setFamily(family);
        when(youthRepository.findById(emptyYouthDto.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> youthService.editYouth(emptyYouthDto))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage("the required youth is not present");
        verify(youthRepository,times(1)).findById(emptyYouthDto.getId());
        verify(youthMapper, times(0)).youthDtoToYouth(eq(emptyYouthDto),any(Youth.class)
                ,any(FamilyService.class), any(StreetService.class));
        verify(youthRepository, times(0)).save(fullYouth);
    }

    @Test
    void testEditYouthWithNullId() {
        Youth fullYouth = youthsTable.get(0);
        emptyYouthDto.setId(null);
        fullYouth.setFirstName("isaak");
        fullYouth.setLastName("vector");
        Family family = Family.builder().id(1L).name("mark").family_level(3).joiningYear(2021).build();
        fullYouth.setFamily(family);
        assertThatThrownBy(() -> youthService.editYouth(emptyYouthDto))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage("the youth id is null");
        verify(youthRepository,times(0)).findById(emptyYouthDto.getId());
        verify(youthMapper, times(0)).youthDtoToYouth(eq(emptyYouthDto),any(Youth.class)
                ,any(FamilyService.class), any(StreetService.class));
        verify(youthRepository, times(0)).save(fullYouth);
    }

    @Test
    void testGetAll() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO();
        List<Youth> mockedYouthList = List.of(youthsTable.get(0), youthsTable.get(1), youthsTable.get(2),
                youthsTable.get(3), youthsTable.get(4), youthsTable.get(5));
        Page<Youth> mockedPage = new PageImpl<>(mockedYouthList); // Create a mocked Page object
        when(youthRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(mockedPage);
        // TODO: Create a list of mocked YouthIntermediateDTO objects
        List<YouthMidLevelDTO> mockedIntermediateDtoList = List.of(youthMidLevelDTOTable.get(0),
                youthMidLevelDTOTable.get(1), youthMidLevelDTOTable.get(2), youthMidLevelDTOTable.get(3),
                youthMidLevelDTOTable.get(4), youthMidLevelDTOTable.get(5));
        Page<YouthMidLevelDTO> mockedIntermediateDtoPage = new PageImpl<>(mockedIntermediateDtoList);
        when(youthMapper.youthsToPageYouthIntermediateDtos(any(Page.class))).thenReturn(mockedIntermediateDtoPage);
        Page<YouthMidLevelDTO> result = youthService.findAll(youthFiltersDTO);
        assertThat(result).isEqualTo(mockedIntermediateDtoPage);
        verify(youthRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
        verify(youthMapper, times(1)).youthsToPageYouthIntermediateDtos(any(Page.class));
    }

    @Test
    void testGetAllCustomPagination() {
        int pageNumber = 0;
        int pageSize = 2;
        YouthFiltersDTO youthFiltersDTO = YouthFiltersDTO.builder().size(2).page(1).build();
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        youthService.findAll(youthFiltersDTO);
        verify(youthRepository).findAll(any(Specification.class), pageableCaptor.capture());
        Pageable capturedPageable = pageableCaptor.getValue();
        assertThat(capturedPageable.getPageNumber()).isEqualTo(pageNumber);
        assertThat(capturedPageable.getPageSize()).isEqualTo(pageSize);
    }

    @Test
    void testGetAllDefaultPagination() {
        int defaultPageNumber = 0;
        int defaultPageSize = 10;
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO();
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        youthService.findAll(youthFiltersDTO);
        verify(youthRepository).findAll(any(Specification.class), pageableCaptor.capture());
        Pageable capturedPageable = pageableCaptor.getValue();
        assertThat(capturedPageable.getPageNumber()).isEqualTo(defaultPageNumber);
        assertThat(capturedPageable.getPageSize()).isEqualTo(defaultPageSize);
    }
    //TODO add tests for adding and editing youths with wrong family and street ids
}