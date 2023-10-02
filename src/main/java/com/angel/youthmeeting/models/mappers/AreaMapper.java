
package com.angel.youthmeeting.models.mappers;

import com.angel.youthmeeting.models.dtos.LightDTO;
import com.angel.youthmeeting.models.entities.Area;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AreaMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Area mapLightDTO(LightDTO areaLightDTO, @MappingTarget Area area);
}
