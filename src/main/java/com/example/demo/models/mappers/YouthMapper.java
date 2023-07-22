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
        if(youthDTO.familyId != null)
            youth.setFamily(familyServices.getFamilyById(youthDTO.familyId));
        if(youthDTO.areaId != null)
            youth.setArea(areaServices.getById(youthDTO.areaId));
        if(youthDTO.streetId != null)
            youth.setStreet(streetServices.getById(youthDTO.streetId));
    }

    @Mapping(target = "familyId", source = "family.id")
    @Mapping(target = "areaId", source = "area.id")
    @Mapping(target = "streetId", source = "street.id")
    YouthDTO youthToYouthDto(Youth youth, @MappingTarget YouthDTO youthDTO);

}
