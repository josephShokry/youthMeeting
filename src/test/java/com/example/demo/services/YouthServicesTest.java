package com.example.demo.services;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.DTOs.YouthIntermediateDTO;
import com.example.demo.models.Family;
import com.example.demo.models.Youth;
import com.example.demo.models.mappers.YouthIntermediateMapper;
import com.example.demo.models.mappers.YouthMapper;
import com.example.demo.repositories.YouthRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    @InjectMocks
    private YouthServices youthService;
    @MockBean private YouthRepository youthRepository;
    @MockBean private FamilyServices familyServices;
    @MockBean private AreaServices areaServices;
    @MockBean private StreetServices streetServices;
    @MockBean private YouthMapper youthMapper;
    @MockBean private YouthIntermediateMapper youthIntermediateMapper;
    //Data
    Youth youth1 = new Youth(null, "Joseph",  "Shokry","2002-04-09","01284024832");
    Youth youth2 = new Youth(null, "Isaac",  "Vector","2003-09-04","01278497512");
    Youth youth3 = new Youth(null, "Adel", "Makram","1998-10-14","01579486321");
    Youth youth4 = new Youth(null, "Fady", "Shokry","2003-05-04","01147547894");
    Youth youth5 = new Youth(null, "Kiro", "Soliman","2004-11-22","01075471369");
    Youth youth6 = new Youth(null, "Jolie", "Atef","2001-01-30","01578945617");

    YouthIntermediateDTO youthIntermediateDTO1 = new YouthIntermediateDTO(1,"Joseph","Shokry",3,3);
    YouthIntermediateDTO youthIntermediateDTO2 = new YouthIntermediateDTO(1,"Isaac", "Vector",2,2);
    YouthIntermediateDTO youthIntermediateDTO3 = new YouthIntermediateDTO(1,"Adel", "Makram",1,1);
    YouthIntermediateDTO youthIntermediateDTO4 = new YouthIntermediateDTO(1,"Fady", "Shokry",4,4);
    YouthIntermediateDTO youthIntermediateDTO5 = new YouthIntermediateDTO(1,"Kiro", "Soliman",2,2);
    YouthIntermediateDTO youthIntermediateDTO6 = new YouthIntermediateDTO(1,"Jolie", "Atef",3,3);

    YouthDTO emptyYouthDto = new YouthDTO();


    @Test
    void testAddYouth() {
        //mock
        when(youthMapper.youthDtoToYouth(eq(emptyYouthDto),any(Youth.class),any(FamilyServices.class),
                any(AreaServices.class),any(StreetServices.class))).thenReturn(youth1);
        //call
        youthService.addYouth(emptyYouthDto);
        //verify
        verify(youthMapper, times(1)).youthDtoToYouth(eq(emptyYouthDto),any(Youth.class)
                ,any(FamilyServices.class), any(AreaServices.class),any(StreetServices.class));
        verify(youthRepository, times(1)).save(youth1);
    }
    @Test
    void testGetYouthByCorrectId() {
        when(youthRepository.findById(1)).thenReturn(Optional.of(youth1));
        assertThat(youthService.getYouthById(1)).isEqualTo(youth1);
        verify(youthRepository,times(1)).findById(1);
    }

    @Test
    void testGetYouthByIdWithWrongId() {
        when(youthRepository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> youthService.getYouthById(1))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage("the required youth is not present");
        verify(youthRepository).findById(1);
    }

    @Test
    void testGetYouthDtoByCorrectId() {
        YouthDTO fullYouthDto = new YouthDTO();
        fullYouthDto.firstName = "Joseph";
        fullYouthDto.lastName = "Shokry";
        fullYouthDto.id = 1;
        fullYouthDto.phoneNumber = "01284024832";

        when(youthRepository.findById(1)).thenReturn(Optional.of(youth1));
        when(youthMapper.youthToYouthDto(eq(youth1), any(YouthDTO.class))).thenReturn(fullYouthDto);
        assertThat(youthService.getYouthDtoById(1)).isEqualTo(fullYouthDto);
        verify(youthRepository,times(1)).findById(1);
        verify(youthMapper,times(1)).youthToYouthDto(eq(youth1),any(YouthDTO.class));
    }
    @Test
    void testGetYouthDtoByIdWithWrongId() {
        when(youthRepository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> youthService.getYouthDtoById(1))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage("the required youth is not present");
        verify(youthRepository).findById(1);
        verify(youthMapper, times(0)).youthToYouthDto(any(Youth.class),any(YouthDTO.class));
    }

    @Test
    void testEditYouth() {
        Youth fullYouth = youth1;
        Integer youthId = 1;
        fullYouth.setFirstName("isaak");
        fullYouth.setLastName("vector");
        Family family = new Family(1,"mark",3,null,2021);
        fullYouth.setFamily(family);

        //mock
        when(youthRepository.findById(youthId)).thenReturn(Optional.of(youth1));
        when(youthMapper.youthDtoToYouth(eq(emptyYouthDto),eq(youth1),any(FamilyServices.class),
                any(AreaServices.class),any(StreetServices.class))).thenReturn(fullYouth);
        //call
        youthService.editYouth(youthId, emptyYouthDto);
        //verify
        verify(youthMapper, times(1)).youthDtoToYouth(eq(emptyYouthDto),any(Youth.class)

                ,any(FamilyServices.class), any(AreaServices.class),any(StreetServices.class));
        verify(youthRepository, times(1)).save(fullYouth);
        verify(youthRepository,times(1)).findById(youthId);
    }

    @Test
    void testGetAll() {
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null,null,null,null,null,null,null);
        List<Youth> mockedYouthList = List.of(youth1, youth2, youth3, youth4, youth5, youth6);
        Page<Youth> mockedPage = new PageImpl<>(mockedYouthList); // Create a mocked Page object
        when(youthRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(mockedPage);
        List<YouthIntermediateDTO> mockedIntermediateDtoList = List.of(youthIntermediateDTO1, youthIntermediateDTO2, youthIntermediateDTO3, youthIntermediateDTO4, youthIntermediateDTO5, youthIntermediateDTO6); // Create a list of mocked YouthIntermediateDTO objects
        Page<YouthIntermediateDTO> mockedIntermediateDtoPage = new PageImpl<>(mockedIntermediateDtoList);
        when(youthIntermediateMapper.youthsToPageYouthIntermediateDtos(any(Page.class))).thenReturn(mockedIntermediateDtoPage);
        Page<YouthIntermediateDTO> result = youthService.getAll(youthFiltersDTO);
        assertThat(result).isEqualTo(mockedIntermediateDtoPage);
        verify(youthRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
        verify(youthIntermediateMapper, times(1)).youthsToPageYouthIntermediateDtos(any(Page.class));
    }

    @Test
    void testGetAllCustomPagination() {
        int pageNumber = 0;
        int pageSize = 2;
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null, null, null, null, null, 2, 1);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        youthService.getAll(youthFiltersDTO);
        verify(youthRepository).findAll(any(Specification.class), pageableCaptor.capture());
        Pageable capturedPageable = pageableCaptor.getValue();
        assertThat(capturedPageable.getPageNumber()).isEqualTo(pageNumber);
        assertThat(capturedPageable.getPageSize()).isEqualTo(pageSize);
    }

    @Test
    void testGetAllDefaultPagination() {
        int defaultPageNumber = 0;
        int defaultPageSize = 10;
        YouthFiltersDTO youthFiltersDTO = new YouthFiltersDTO(null, null, null, null, null, null, null);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        youthService.getAll(youthFiltersDTO);
        verify(youthRepository).findAll(any(Specification.class), pageableCaptor.capture());
        Pageable capturedPageable = pageableCaptor.getValue();
        assertThat(capturedPageable.getPageNumber()).isEqualTo(defaultPageNumber);
        assertThat(capturedPageable.getPageSize()).isEqualTo(defaultPageSize);
    }
}