package com.angel.youthmeeting.models.mappers;

import com.angel.youthmeeting.models.dtos.UserDTO;
import com.angel.youthmeeting.services.implementations.ServantService;
import com.angel.youthmeeting.models.entities.User;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User mapToUser(UserDTO userDTO, @MappingTarget User user, @Context ServantService servantService);

    @AfterMapping
    default void attachObjects(UserDTO userDTO, @MappingTarget User user, @Context ServantService servantService){
        Optional.ofNullable(userDTO.getPersonId()).ifPresent(
                servantId -> user.setPerson(servantService.getServantById(servantId)));
    }

    UserDTO mapToUserDTO(User user, @MappingTarget UserDTO userDTO);
}
