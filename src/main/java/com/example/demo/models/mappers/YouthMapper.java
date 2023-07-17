package com.example.demo.models.mappers;
import com.example.demo.models.DTOs.PersonDTO;
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
    @Mapping(target = "dayOfBirth",expression = "java(java.time.LocalDate.parse(personDTO.dayOfBirth))")
    Youth youthDtoToYouth(PersonDTO personDTO, @MappingTarget Youth youth,
                          @Context FamilyServices familyServices,
                          @Context AreaServices areaServices,
                          @Context StreetServices streetServices);


    @AfterMapping
    default void get(PersonDTO personDTO, @MappingTarget Youth youth,
                     @Context FamilyServices familyServices,
                     @Context AreaServices areaServices,
                     @Context StreetServices streetServices){
        youth.setFamily(familyServices.getFamilyById(personDTO.familyId));
//        youth.setArea(areaServices.getById(personDTO.areaId));
//        youth.setStreet(streetServices.getById(personDTO.streetId));
    }

    @Mapping(target = "familyId", source = "family.id")
    PersonDTO youthToYouthDto(Youth youth, @MappingTarget PersonDTO personDTO);

}
