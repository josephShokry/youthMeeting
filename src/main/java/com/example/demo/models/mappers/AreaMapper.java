
package com.example.demo.models.mappers;

import com.example.demo.models.dtos.LightDTO;
import com.example.demo.models.entities.Area;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AreaMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Area mapLightDTO(LightDTO areaLightDTO, @MappingTarget Area area);
}
