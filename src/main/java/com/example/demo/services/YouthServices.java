package com.example.demo.services;

import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.DTOs.YouthIntermediateDTO;
import com.example.demo.models.mappers.LightYouthMapper;
import com.example.demo.models.mappers.YouthIntermediateMapper;
import com.example.demo.models.mappers.YouthMapper;
import com.example.demo.models.Youth;
import com.example.demo.repositories.YouthRepository;
import com.example.demo.repositories.YouthSpecificationImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class YouthServices {
    @Autowired
    private YouthRepository youthRepository;
    @Autowired
    private FamilyServices familyServices;
    @Autowired

    private AreaServices areaServices;
    @Autowired

    private StreetServices streetServices;
    private final YouthMapper youthMapper = Mappers.getMapper(YouthMapper.class);
    private final LightYouthMapper lightYouthMapper = Mappers.getMapper(LightYouthMapper.class);
    private final YouthIntermediateMapper youthIntermediateMapper = Mappers.getMapper(YouthIntermediateMapper.class);



    public boolean addYouth(YouthDTO youthDTO) {
        Youth youth = new Youth();
        youthMapper.youthDtoToYouth(youthDTO, youth, familyServices, areaServices, streetServices);
        youthRepository.save(youth);
        return true;
    }
    public Youth getYouthById(int youthId){
        return youthRepository.findById(youthId).get();
    }
    public YouthDTO getYouthDtoById(int youthId){
        return youthMapper.youthToYouthDto(getYouthById(youthId), new YouthDTO());
    }

    public String getArea (int youthId) {return getYouthById(youthId).getArea().getAreaName();}
    public String getStreet(int youthId) {
        return getYouthById(youthId).getStreet().getStreetName();
    }


    public Page<YouthIntermediateDTO> getAll(int page, int size, YouthFiltersDTO youthFiltersDTO) {
        Pageable paging = PageRequest.of(page, size);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> youths = youthRepository.findAll(specification, paging);
        return youthIntermediateMapper.youthsToPageYouthIntermediateDtos(youths);
    }

    public boolean editYouth(int youthId, YouthDTO youthDTO) {
        Youth youth = getYouthById(youthId);
        youthMapper.youthDtoToYouth(youthDTO, youth, familyServices, areaServices, streetServices);
        youthRepository.save(youth);
        return true;
    }
}
