package com.example.demo.services.implementations;

import com.example.demo.exceptions.exceptions.DataNotFoundException;
import com.example.demo.models.dtos.YouthDTO;
import com.example.demo.models.dtos.YouthFiltersDTO;
import com.example.demo.models.dtos.YouthMidLevelDTO;
import com.example.demo.models.entities.Youth;
import com.example.demo.models.mappers.YouthMapper;
import com.example.demo.repositories.YouthRepository;
import com.example.demo.repositories.YouthSpecificationImpl;
import com.example.demo.services.IYouthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class YouthService implements IYouthService {
    @Autowired
    private YouthRepository youthRepository;
    @Autowired
    private FamilyService familyService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private FatherService fatherService;
    @Autowired
    private YouthMapper youthMapper;

    public Boolean addYouth(YouthDTO youthDTO) {
        Youth youth = new Youth();
        youthRepository.save(youthMapper.mapYouthDTO(youthDTO, youth, familyService, streetService));
        return true;
    }
    public Youth findYouthById(Long youthId){
        youthId = Optional.ofNullable(youthId).orElseThrow(() -> new DataNotFoundException("validation.error.youthId"));
        return youthRepository.findById(youthId).orElseThrow(
                () -> new DataNotFoundException("validation.error.youth"));
    }
    public YouthDTO findYouthDtoById(Long youthId){
        return youthMapper.mapYouth(findYouthById(youthId), new YouthDTO());
    }

    public Page<YouthMidLevelDTO> findAll(YouthFiltersDTO youthFiltersDTO) {
        youthFiltersDTO.setPage(Optional.ofNullable(youthFiltersDTO.getPage()).map(page -> page - 1).orElse(0));
        youthFiltersDTO.setSize(Optional.ofNullable(youthFiltersDTO.getSize()).orElse(10));
        Pageable paging = PageRequest.of(youthFiltersDTO.getPage(), youthFiltersDTO.getSize());
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> youths = youthRepository.findAll(specification, paging);
        return youthMapper.mapPageOfYouths(youths);
    }

    public Boolean editYouth(YouthDTO youthDTO) {
        Youth youth = findYouthById(youthDTO.getId());
        youthRepository.save(youthMapper.mapYouthDTO(youthDTO, youth, familyService, streetService));
        return true;
    }
}
