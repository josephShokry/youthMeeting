package com.example.demo.services;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.DTOs.YouthIntermediateDTO;
import com.example.demo.models.Youth;
import com.example.demo.models.mappers.YouthIntermediateMapper;
import com.example.demo.models.mappers.YouthMapper;
import com.example.demo.repositories.YouthRepository;
import com.example.demo.repositories.YouthSpecificationImpl;
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
//    private final YouthMapper youthMapper = Mappers.getMapper(YouthMapper.class);
    @Autowired
    private YouthMapper youthMapper;
    //private final YouthIntermediateMapper youthIntermediateMapper = Mappers.getMapper(YouthIntermediateMapper.class);
    @Autowired
    private  YouthIntermediateMapper youthIntermediateMapper;


    public void addYouth(YouthDTO youthDTO) {
        Youth youth = youthMapper.youthDtoToYouth(youthDTO, new Youth(), familyServices, areaServices, streetServices);
        youthRepository.save(youth);
    }
    public Youth getYouthById(Integer youthId){
        Youth youth = youthRepository.findById(youthId).orElseThrow(
                () -> new DataNotFoundException("the required youth is not present"));
        return youth;
    }
    public YouthDTO getYouthDtoById(Integer youthId){
        return youthMapper.youthToYouthDto(getYouthById(youthId), new YouthDTO());
    }

    public Page<YouthIntermediateDTO> getAll(YouthFiltersDTO youthFiltersDTO) {
        //TODO: Alternative solutions which is better
//        youthFiltersDTO.setPage(Optional.ofNullable(youthFiltersDTO.getPage()).orElse(0));
//        youthFiltersDTO.setSize(Optional.ofNullable(youthFiltersDTO.getSize()).orElse(10));
        youthFiltersDTO.page = (youthFiltersDTO.page == null || youthFiltersDTO.page == 0)? 0 : youthFiltersDTO.page-1;
        youthFiltersDTO.size = (youthFiltersDTO.size == null || youthFiltersDTO.size == 0)? 10 : youthFiltersDTO.size;
        Pageable paging = PageRequest.of(youthFiltersDTO.page, youthFiltersDTO.size);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> youths = youthRepository.findAll(specification, paging);
        return youthIntermediateMapper.youthsToPageYouthIntermediateDtos(youths);
    }

    public void editYouth(Integer youthId, YouthDTO youthDTO) {
        Youth youth = getYouthById(youthId);
        youthMapper.youthDtoToYouth(youthDTO, youth, familyServices, areaServices, streetServices);
        youthRepository.save(youth);
    }
}
