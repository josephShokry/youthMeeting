package com.example.demo.models.mappers;
import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.Youth;
import com.example.demo.services.AreaServices;
import com.example.demo.services.FamilyServices;
import com.example.demo.services.StreetServices;
import org.mapstruct.*;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YouthMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
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
//        youth.setArea(areaServices.getById(personDTO.areaId));
//        youth.setStreet(streetServices.getById(personDTO.streetId));
    }

    @Mapping(target = "familyId", source = "family.id")
    YouthDTO youthToYouthDto(Youth youth, @MappingTarget YouthDTO youthDTO);

}
