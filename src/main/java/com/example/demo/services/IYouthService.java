package com.example.demo.services;

import com.example.demo.models.dtos.YouthDTO;
import com.example.demo.models.dtos.YouthFiltersDTO;
import com.example.demo.models.dtos.YouthMidLevelDTO;
import com.example.demo.models.entities.Youth;
import org.springframework.data.domain.Page;

public interface IYouthService {

    Boolean addYouth(YouthDTO youthDTO);

    Youth findYouthById(Long youthId);

    YouthDTO findYouthDtoById(Long youthId);

    Page<YouthMidLevelDTO> findAll(YouthFiltersDTO youthFiltersDTO);

    Boolean editYouth(YouthDTO youthDTO);
}