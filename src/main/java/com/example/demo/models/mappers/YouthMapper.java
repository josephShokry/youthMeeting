package com.example.demo.models.mappers;
import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthIntermediateDTO;
import com.example.demo.models.Youth;
import com.example.demo.services.FamilyService;
import com.example.demo.services.StreetService;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YouthMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "dayOfBirth",expression = "java(youthDTO.dayOfBirth != null ?" +
            " java.time.LocalDate.parse(youthDTO.dayOfBirth) : null)")
    Youth youthDtoToYouth(YouthDTO youthDTO, @MappingTarget Youth youth,
                          @Context FamilyService familyService,
                          @Context StreetService streetService);

    @AfterMapping
    default void attachEntities(YouthDTO youthDTO, @MappingTarget Youth youth,
                                @Context FamilyService familyService,
                                @Context StreetService streetService){
        Optional.ofNullable(youthDTO.familyId).ifPresent(
                youthId -> youth.setFamily(familyService.findFamilyById(youthId)));
        Optional.ofNullable(youthDTO.streetId).ifPresent(
                streetId -> youth.setStreet(streetService.findById(streetId)));
    }
    @Mapping(target = "familyId", source = "family.id")
    @Mapping(target = "streetId", source = "street.id")
    YouthDTO youthToYouthDto(Youth youth, @MappingTarget YouthDTO youthDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "familyId", source = "family.id")
    YouthIntermediateDTO youthToYouthIntermediateDto(Youth youth, @MappingTarget YouthIntermediateDTO youthIntermediateDTO);

    default Iterable<YouthIntermediateDTO> youthsToYouthIntermediateDtos(Iterable<Youth> youths){
        return StreamSupport.stream(youths.spliterator(), false)
                .map(entity -> youthToYouthIntermediateDto(entity,new YouthIntermediateDTO()))
                .collect(Collectors.toList());
    }
    default Page<YouthIntermediateDTO> youthsToPageYouthIntermediateDtos(Page<Youth> YouthsPage) {
        List<YouthIntermediateDTO> dtoList = (List<YouthIntermediateDTO>) youthsToYouthIntermediateDtos(YouthsPage.getContent());
        return new PageImpl<>(dtoList, YouthsPage.getPageable(), YouthsPage.getTotalElements());
    }
}
