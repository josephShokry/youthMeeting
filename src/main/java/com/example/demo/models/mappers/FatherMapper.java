package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.FatherDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.entities.Father;
import org.mapstruct.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FatherMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Father fatherDtoToFather(FatherDTO fatherDTO, @MappingTarget Father father);
    FatherDTO fatherToFatherDto(Father father, @MappingTarget FatherDTO fatherDTO);
    @Mapping(source = "firstName", target = "name")
    LightDTO fatherToLightDto(Father father, @MappingTarget LightDTO lightDTO);

    default Iterable<LightDTO> fathersToLightDtos(Iterable<Father> families){
        return StreamSupport.stream(families.spliterator(), false)
                .map(entity -> fatherToLightDto(entity,new LightDTO()))
                .collect(Collectors.toList());
    }
}
