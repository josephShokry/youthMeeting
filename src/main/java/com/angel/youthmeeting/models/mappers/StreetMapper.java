package com.angel.youthmeeting.models.mappers;

import com.angel.youthmeeting.models.dtos.StreetDTO;
import com.angel.youthmeeting.services.implementations.AreaService;
import com.angel.youthmeeting.models.entities.Street;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StreetMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Street mapStreetDTO(StreetDTO streetDTO, @MappingTarget Street street, AreaService areaService);

    @AfterMapping
    default void attachArea(StreetDTO streetDTO, @MappingTarget Street street, AreaService areaService){
        street.setArea(areaService.findById(streetDTO.getAreaId()));
    }
}
