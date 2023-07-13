package com.example.demo.models.mappers;
import com.example.demo.models.DTOs.PersonDTO;
import com.example.demo.models.Family;
import com.example.demo.models.Youth;
import com.example.demo.services.FamilyServices;
import org.mapstruct.*;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface YouthMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    Youth youthDtoToYouth(PersonDTO personDTO, @MappingTarget Youth youth,
                                          @Context FamilyServices familyServices);


    @AfterMapping
    default void get(PersonDTO personDTO, @MappingTarget Youth youth,
                     @Context FamilyServices familyServices ){
        youth.setFamily(familyServices.getFamilyById(personDTO.familyId));
    }


    @Mapping(source = "family", target = "family")
    PersonDTO youthsToYouthLightDto(Youth youth, @MappingTarget PersonDTO personDTO);




























////////////////////////////////////////////
//    // lightDTO for youth
//    @Mapping(expression = "java(youth.getFirstName() + ' ' + youth.getLastName())", target = "name")
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    YouthLightDTO youthToYouthLightDto(Youth youth, @MappingTarget YouthLightDTO youthLightDTO);
////    @Mapping(expression = "java(youth.getFirstName() + ' ' + youth.getLastName())", target = "name")
//    default Iterable<YouthLightDTO> youthsToYouthLightDto(Iterable<Youth> youths){
//        return StreamSupport.stream(youths.spliterator(), false)
//                .map(entity -> youthToYouthLightDto(entity,new YouthLightDTO()))
//                .collect(Collectors.toList());
//    }
/////////////////////////////////////
    // methods for family
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    Family familyDtoToFamily(FamilyDTO familyDTO, @MappingTarget Family family);
//    FamilyDTO familyToFamilyDto(Family family, @MappingTarget FamilyDTO familyDTO);
////////////////////////////////////////////

}
