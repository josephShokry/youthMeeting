
package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.entities.Area;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AreaMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Area getAreaFromDto(LightDTO areaLightDTO, @MappingTarget Area area);
}
