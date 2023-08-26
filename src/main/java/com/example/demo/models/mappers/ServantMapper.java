package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.ServantDTO;
import com.example.demo.models.entities.Servant;
import com.example.demo.services.FamilyServices;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServantMapper {
    Servant servantDtoToServant(ServantDTO servantDTO, @MappingTarget Servant servant, @Context FamilyServices familyServices);
    @AfterMapping
    default void attachObjects(ServantDTO servantDTO, @MappingTarget Servant servant, @Context FamilyServices familyServices){
        Optional.ofNullable(servantDTO.familyId).ifPresent(
                familyId -> servant.setFamily(familyServices.getFamilyById(familyId)));
    }
    ServantDTO servantToServantDto(Servant servant, @MappingTarget ServantDTO servantDTO);
}
