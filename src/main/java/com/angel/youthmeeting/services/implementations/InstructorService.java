package com.angel.youthmeeting.services.implementations;

import com.angel.youthmeeting.exceptions.exceptions.DataNotFoundException;
import com.angel.youthmeeting.models.dtos.InstructorDTO;
import com.angel.youthmeeting.models.entities.Instructor;
import com.angel.youthmeeting.models.mappers.InstructorMapper;
import com.angel.youthmeeting.repositories.InstructorRepository;
import com.angel.youthmeeting.services.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstructorService implements IInstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorMapper instructorMapper;

    @Autowired
    private FatherService fatherService;

    @Autowired
    private ServantService servantService;

    public Long addInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        instructorRepository.save(instructorMapper.mapInstructorDTO(instructorDTO, instructor, fatherService, servantService));
        return instructor.getId();
    }

    public Instructor findById(Long instructorId) {
        instructorId = Optional.ofNullable(instructorId).orElseThrow(() -> new DataNotFoundException("validation.error.instructorId"));
        return instructorRepository.findById(instructorId).orElseThrow(
                ()-> new DataNotFoundException("validation.error.instructor"));
    }
}
