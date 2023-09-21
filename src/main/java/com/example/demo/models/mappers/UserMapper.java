package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.UserDTO;
import com.example.demo.models.entities.User;
import com.example.demo.services.implementations.ServantService;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userDtoToUser(UserDTO userDTO, @MappingTarget User user, @Context ServantService servantService);
    @AfterMapping
    default void attachObjects(UserDTO userDTO, @MappingTarget User user, @Context ServantService servantService){
        Optional.ofNullable(userDTO.personId).ifPresent(
                servantId -> user.setPerson(servantService.getServantById(servantId)));
    }
    UserDTO userToUserDto(User user, @MappingTarget UserDTO userDTO);
}
