package com.example.demo.services;

import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.DTOs.YouthIntermediateDTO;
import com.example.demo.models.entities.Youth;
import org.springframework.data.domain.Page;

public interface IYouthService {
    void addYouth(YouthDTO youthDTO);
    Youth findYouthById(Long youthId);
    YouthDTO findYouthDtoById(Long youthId);
    Page<YouthIntermediateDTO> findAll(YouthFiltersDTO youthFiltersDTO);
    void editYouth(YouthDTO youthDTO);
}