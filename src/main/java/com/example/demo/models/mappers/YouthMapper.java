package com.example.demo.models.mappers;
import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.entities.Youth;
import com.example.demo.services.implementations.FamilyService;
import com.example.demo.services.implementations.FatherService;
import com.example.demo.services.implementations.StreetService;
import org.mapstruct.*;

import java.util.Optional;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YouthMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "dayOfBirth",expression = "java(youthDTO.dayOfBirth != null ?" +
            " java.time.LocalDate.parse(youthDTO.dayOfBirth) : null)")
    Youth youthDtoToYouth(YouthDTO youthDTO, @MappingTarget Youth youth,
                          @Context FamilyService familyService,
                          @Context StreetService streetService,
                          @Context FatherService fatherService);


    @AfterMapping
    default void get(YouthDTO youthDTO, @MappingTarget Youth youth,
                     @Context FamilyService familyService,
                     @Context StreetService streetService,
                     @Context FatherService fatherService){
        Optional.ofNullable(youthDTO.familyId).ifPresent(
                youthId -> youth.setFamily(familyService.getFamilyById(youthId)));
        Optional.ofNullable(youthDTO.streetId).ifPresent(
                streetId -> youth.setStreet(streetService.getById(streetId)));
        Optional.ofNullable(youthDTO.fatherId).ifPresent(
                fatherId -> youth.setFather(fatherService.getById(fatherId)));
    }

    @Mapping(target = "familyId", source = "family.id")
    @Mapping(target = "streetId", source = "street.id")
    @Mapping(target = "fatherId", source = "father.id")
    YouthDTO youthToYouthDto(Youth youth, @MappingTarget YouthDTO youthDTO);

}
