package com.angel.youthmeeting.models.mappers;

import com.angel.youthmeeting.models.dtos.StreetDTO;
import com.angel.youthmeeting.models.entities.Street;
import com.angel.youthmeeting.services.implementations.AreaService;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StreetMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Street mapStreetDTO(StreetDTO streetDTO, @MappingTarget Street street, AreaService areaService);

    @AfterMapping
    default void attachArea(StreetDTO streetDTO, @MappingTarget Street street, AreaService areaService){
        street.setArea(areaService.findById(streetDTO.getAreaId()));
    }
}
