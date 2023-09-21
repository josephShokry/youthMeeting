package com.example.demo.models.mappers;

import com.example.demo.models.dtos.YouthDTO;
import com.example.demo.models.dtos.YouthMidLevelDTO;
import com.example.demo.models.entities.Youth;
import com.example.demo.services.implementations.FamilyService;
import com.example.demo.services.implementations.StreetService;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YouthMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "dayOfBirth", expression = "java(youthDTO.getDayOfBirth() != null ?" +
            " java.time.LocalDate.parse(youthDTO.getDayOfBirth()) : null)")
    Youth mapYouthDTO(YouthDTO youthDTO, @MappingTarget Youth youth,
                      FamilyService familyService, StreetService streetService);

    @AfterMapping
    default void attachEntities(YouthDTO youthDTO, @MappingTarget Youth youth,
                                FamilyService familyService, StreetService streetService){
        Optional.ofNullable(youthDTO.getFamilyId()).ifPresent(
                youthId -> youth.setFamily(familyService.findFamilyById(youthId)));
        Optional.ofNullable(youthDTO.getStreetId()).ifPresent(
                streetId -> youth.setStreet(streetService.findById(streetId)));
    }
    @Mapping(target = "familyId", source = "family.id")
    @Mapping(target = "streetId", source = "street.id")
    YouthDTO mapYouth(Youth youth, @MappingTarget YouthDTO youthDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "familyId", source = "family.id")
    YouthMidLevelDTO mapYouth(Youth youth, @MappingTarget YouthMidLevelDTO youthMidLevelDTO);
    List<YouthMidLevelDTO> mapListOfYouths(List<Youth> youths);
    default Page<YouthMidLevelDTO> mapPageOfYouths(Page<Youth> youthsPage) {
        List<YouthMidLevelDTO> dtoList = mapListOfYouths(youthsPage.getContent());
        return new PageImpl<>(dtoList, youthsPage.getPageable(), youthsPage.getTotalElements());
    }
}
