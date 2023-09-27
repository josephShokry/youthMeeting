package com.example.demo.models.mappers;

import com.example.demo.models.dtos.FatherDTO;
import com.example.demo.models.entities.Father;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FatherMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Father mapToFather(FatherDTO fatherDTO, @MappingTarget Father father);

    FatherDTO mapToFatherDTO(Father father, @MappingTarget FatherDTO fatherDTO);
}
