package com.angle.youthmeeting.models.mappers;

import com.angle.youthmeeting.models.entities.Father;
import com.angle.youthmeeting.models.dtos.FatherDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FatherMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Father mapToFather(FatherDTO fatherDTO, @MappingTarget Father father);

    FatherDTO mapToFatherDTO(Father father, @MappingTarget FatherDTO fatherDTO);
}
