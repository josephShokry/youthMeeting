package com.example.demo.services.implementations;

import com.example.demo.exceptions.exceptions.DataNotFoundException;
import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.DTOs.YouthIntermediateDTO;
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
    private YouthMapper youthMapper;

    public void addYouth(YouthDTO youthDTO) {
        Youth youth = youthMapper.youthDtoToYouth(youthDTO, new Youth(), familyService, streetService);
        youthRepository.save(youth);
    }
    public Youth findYouthById(Long youthId){
        // TODO: need something to catch this exceptions
        Optional.ofNullable(youthId).orElseThrow(() -> new DataNotFoundException("the youth id is null"));
        return youthRepository.findById(youthId).orElseThrow(
                () -> new DataNotFoundException("the required youth is not present"));
    }
    public YouthDTO findYouthDtoById(Long youthId){
        return youthMapper.youthToYouthDto(findYouthById(youthId), new YouthDTO());
    }

    public Page<YouthIntermediateDTO> findAll(YouthFiltersDTO youthFiltersDTO) {
        youthFiltersDTO.setPage(Optional.ofNullable(youthFiltersDTO.getPage()).map(page -> page - 1).orElse(0));
        youthFiltersDTO.setSize(Optional.ofNullable(youthFiltersDTO.getSize()).orElse(10));
        Pageable paging = PageRequest.of(youthFiltersDTO.page, youthFiltersDTO.size);
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);
        Page<Youth> youths = youthRepository.findAll(specification, paging);
        return youthMapper.youthsToPageYouthIntermediateDtos(youths);
    }

    public void editYouth(YouthDTO youthDTO) {
        Youth youth = findYouthById(youthDTO.id);
        youthMapper.youthDtoToYouth(youthDTO, youth, familyService, streetService);
        youthRepository.save(youth);
    }
}
