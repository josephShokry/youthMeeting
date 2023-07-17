package com.example.demo.models.mappers;
import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.Youth;
import com.example.demo.services.AreaServices;
import com.example.demo.services.FamilyServices;
import com.example.demo.services.StreetServices;
import org.mapstruct.*;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YouthMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dayOfBirth",expression = "java(java.time.LocalDate.parse(youthDTO.dayOfBirth))")
    Youth youthDtoToYouth(YouthDTO youthDTO, @MappingTarget Youth youth,
                          @Context FamilyServices familyServices,
                          @Context AreaServices areaServices,
                          @Context StreetServices streetServices);


    @AfterMapping
    default void get(YouthDTO youthDTO, @MappingTarget Youth youth,
                     @Context FamilyServices familyServices,
                     @Context AreaServices areaServices,
                     @Context StreetServices streetServices){
        youth.setFamily(familyServices.getFamilyById(youthDTO.familyId));
        youth.setArea(areaServices.getById(youthDTO.areaId));
        youth.setStreet(streetServices.getById(youthDTO.streetId));
    }

    @Mapping(target = "familyId", source = "family.id")
    @Mapping(target = "areaId", source = "area.id")
    @Mapping(target = "streetId", source = "street.id")
    YouthDTO youthToYouthDto(Youth youth, @MappingTarget YouthDTO youthDTO);

}
