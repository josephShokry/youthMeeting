package com.angel.youthmeeting.models.mappers;

import com.angel.youthmeeting.models.dtos.YouthDTO;
import com.angel.youthmeeting.models.dtos.YouthMidLevelDTO;
import com.angel.youthmeeting.models.entities.Youth;
import com.angel.youthmeeting.services.implementations.FamilyService;
import com.angel.youthmeeting.services.implementations.FatherService;
import com.angel.youthmeeting.services.implementations.StreetService;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
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
                      FamilyService familyService, StreetService streetService, FatherService fatherService);

    @AfterMapping
    default void attachEntities(YouthDTO youthDTO, @MappingTarget Youth youth,
                                FamilyService familyService, StreetService streetService, FatherService fatherService){
        Optional.ofNullable(youthDTO.getFamilyId()).ifPresent(
                youthId -> youth.setFamily(familyService.findFamilyById(youthId)));
        Optional.ofNullable(youthDTO.getStreetId()).ifPresent(
                streetId -> youth.setStreet(streetService.findById(streetId)));
        Optional.ofNullable(youthDTO.getFatherId()).ifPresent(
                fatherId -> youth.setFather(fatherService.findById(fatherId)));
    }

    @Mapping(target = "familyId", source = "family.id")
    @Mapping(target = "streetId", source = "street.id")
    @Mapping(target = "fatherId", source = "father.id")
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
