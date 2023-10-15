package com.angel.youthmeeting.models.mappers;

import com.angel.youthmeeting.models.dtos.InstructorDTO;
import com.angel.youthmeeting.models.entities.Instructor;
import com.angel.youthmeeting.services.implementations.FatherService;
import com.angel.youthmeeting.services.implementations.ServantService;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InstructorMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Instructor mapInstructorDTO(InstructorDTO instructorDTO, @MappingTarget Instructor instructor,
                                FatherService fatherService, ServantService servantService);

    @AfterMapping
    default void attachObjects(InstructorDTO instructorDTO, @MappingTarget Instructor instructor,
                               FatherService fatherService, ServantService servantService){
        Optional.ofNullable(instructorDTO.getServantId()).ifPresent(
                servantId -> instructor.setServant(servantService.findServantById(servantId)));
        Optional.ofNullable(instructorDTO.getFatherId()).ifPresent(
                fatherId -> instructor.setFather(fatherService.findById(fatherId)));
    }
}
