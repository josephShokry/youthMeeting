package com.angle.youthmeeting.models.mappers;

import com.angle.youthmeeting.models.dtos.ServantDTO;
import com.angle.youthmeeting.models.entities.Servant;
import com.angle.youthmeeting.services.implementations.FamilyService;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServantMapper {

    Servant mapToServant(ServantDTO servantDTO, @MappingTarget Servant servant, @Context FamilyService familyService);

    @AfterMapping
    default void attachObjects(ServantDTO servantDTO, @MappingTarget Servant servant, @Context FamilyService familyService){
        Optional.ofNullable(servantDTO.getFamilyId()).ifPresent(
                familyId -> servant.setFamily(familyService.findFamilyById(familyId)));
    }

    ServantDTO mapToServantDTO(Servant servant, @MappingTarget ServantDTO servantDTO);
}
