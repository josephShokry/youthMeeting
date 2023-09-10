package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.Youth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-10T14:33:56+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class LightYouthMapperImpl implements LightYouthMapper {

    @Override
    public LightDTO youthToYouthLightDto(Youth youth, LightDTO lightDTO) {
        if ( youth == null ) {
            return null;
        }

        if ( youth.getId() != null ) {
            lightDTO.id = youth.getId();
        }

        lightDTO.name = youth.getFirstName() + ' ' + youth.getLastName();

        return lightDTO;
    }
}
