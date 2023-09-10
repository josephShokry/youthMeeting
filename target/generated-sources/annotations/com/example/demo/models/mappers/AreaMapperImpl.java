package com.example.demo.models.mappers;

import com.example.demo.models.Area;
import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.LightDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-10T14:33:56+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class AreaMapperImpl implements AreaMapper {

    @Override
    public Area getAreaFromDto(AreaDTO areaDTO, Area area) {
        if ( areaDTO == null ) {
            return null;
        }

        if ( areaDTO.id != null ) {
            area.setId( areaDTO.id );
        }
        if ( areaDTO.areaName != null ) {
            area.setAreaName( areaDTO.areaName );
        }

        return area;
    }

    @Override
    public LightDTO areaToLightDto(Area area, LightDTO lightDTO) {
        if ( area == null ) {
            return null;
        }

        lightDTO.name = area.getAreaName();
        lightDTO.id = area.getId();

        return lightDTO;
    }
}
