package com.angel.youthmeeting.models.mappers;

import com.angel.youthmeeting.models.dtos.FatherDTO;
import com.angel.youthmeeting.models.entities.Father;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FatherMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Father mapToFather(FatherDTO fatherDTO, @MappingTarget Father father);

    FatherDTO mapToFatherDTO(Father father, @MappingTarget FatherDTO fatherDTO);
}
