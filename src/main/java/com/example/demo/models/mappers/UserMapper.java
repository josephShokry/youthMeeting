package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.UserDTO;
import com.example.demo.models.User;
import com.example.demo.services.ServantServices;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userDtoToUser(UserDTO userDTO, @MappingTarget User user, @Context ServantServices servantServices);
    @AfterMapping
    default void attachObjects(UserDTO userDTO, @MappingTarget User user, @Context ServantServices servantServices){
        Optional.ofNullable(userDTO.personId).ifPresent(
                servantId -> user.setPerson(servantServices.getServantById(servantId)));
    }
    UserDTO userToUserDto(User user, @MappingTarget UserDTO userDTO);
}
