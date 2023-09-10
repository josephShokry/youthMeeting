package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.models.Street;
import com.example.demo.services.AreaService;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-10T14:33:56+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class StreetMapperImpl implements StreetMapper {

    @Override
    public Street streetDtoToStreet(StreetDTO streetDTO, Street street, AreaService areaService) {
        if ( streetDTO == null ) {
            return null;
        }

        if ( streetDTO.id != null ) {
            street.setId( streetDTO.id );
        }
        if ( streetDTO.streetName != null ) {
            street.setStreetName( streetDTO.streetName );
        }

        attachArea( streetDTO, street, areaService );

        return street;
    }

    @Override
    public LightDTO streetToLightDto(Street street, LightDTO lightDTO) {
        if ( street == null ) {
            return null;
        }

        lightDTO.name = street.getStreetName();
        lightDTO.id = street.getId();

        return lightDTO;
    }
}
