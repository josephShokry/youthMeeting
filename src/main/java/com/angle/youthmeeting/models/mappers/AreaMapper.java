
package com.angle.youthmeeting.models.mappers;

import com.angle.youthmeeting.models.entities.Area;
import com.angle.youthmeeting.models.dtos.LightDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AreaMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Area mapLightDTO(LightDTO areaLightDTO, @MappingTarget Area area);
}
