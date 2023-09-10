package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.Family;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-10T14:33:56+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class FamilyMapperImpl implements FamilyMapper {

    @Override
    public Family familyDtoToFamily(FamilyDTO familyDTO, Family family) {
        if ( familyDTO == null ) {
            return null;
        }

        if ( familyDTO.id != null ) {
            family.setId( familyDTO.id );
        }
        if ( familyDTO.familyName != null ) {
            family.setFamilyName( familyDTO.familyName );
        }
        if ( familyDTO.familyLevel != null ) {
            family.setFamilyLevel( familyDTO.familyLevel );
        }
        if ( familyDTO.joiningYear != null ) {
            family.setJoiningYear( familyDTO.joiningYear );
        }

        return family;
    }

    @Override
    public FamilyDTO familyToFamilyDto(Family family, FamilyDTO familyDTO) {
        if ( family == null ) {
            return null;
        }

        familyDTO.familyName = family.getFamilyName();
        familyDTO.familyLevel = family.getFamilyLevel();
        familyDTO.joiningYear = family.getJoiningYear();
        familyDTO.id = family.getId();

        return familyDTO;
    }

    @Override
    public LightDTO familyToLightDto(Family family, LightDTO lightDTO) {
        if ( family == null ) {
            return null;
        }

        lightDTO.name = family.getFamilyName();
        lightDTO.id = family.getId();

        return lightDTO;
    }
}
