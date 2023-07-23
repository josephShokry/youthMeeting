package com.example.demo.models.mappers;
import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.Youth;
import com.example.demo.services.AreaServices;
import com.example.demo.services.FamilyServices;
import com.example.demo.services.StreetServices;
import org.mapstruct.*;

import java.util.Optional;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YouthMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dayOfBirth",expression = "java(youthDTO.dayOfBirth != null ?" +
            " java.time.LocalDate.parse(youthDTO.dayOfBirth) : null)")
    Youth youthDtoToYouth(YouthDTO youthDTO, @MappingTarget Youth youth,
                          @Context FamilyServices familyServices,
                          @Context AreaServices areaServices,
                          @Context StreetServices streetServices);


    @AfterMapping
    default void get(YouthDTO youthDTO, @MappingTarget Youth youth,
                     @Context FamilyServices familyServices,
                     @Context AreaServices areaServices,
                     @Context StreetServices streetServices){
        Optional.ofNullable(youthDTO.familyId).ifPresent(
                youthId -> youth.setFamily(familyServices.getFamilyById(youthId)));
        Optional.ofNullable(youthDTO.areaId).ifPresent(
                areaId -> youth.setArea(areaServices.getById(areaId)));
        Optional.ofNullable(youthDTO.streetId).ifPresent(
                streetId -> youth.setStreet(streetServices.getById(streetId)));
    }

    @Mapping(target = "familyId", source = "family.id")
    @Mapping(target = "areaId", source = "area.id")
    @Mapping(target = "streetId", source = "street.id")
    YouthDTO youthToYouthDto(Youth youth, @MappingTarget YouthDTO youthDTO);

}
