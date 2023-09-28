package com.angle.youthmeeting.models.mappers;

import com.angle.youthmeeting.models.dtos.StreetDTO;
import com.angle.youthmeeting.models.entities.Street;
import com.angle.youthmeeting.services.implementations.AreaService;
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
