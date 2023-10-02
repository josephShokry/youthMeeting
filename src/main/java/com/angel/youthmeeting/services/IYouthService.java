package com.angel.youthmeeting.services;

import com.angel.youthmeeting.models.dtos.YouthDTO;
import com.angel.youthmeeting.models.dtos.YouthFiltersDTO;
import com.angel.youthmeeting.models.dtos.YouthMidLevelDTO;
import com.angel.youthmeeting.models.entities.Youth;
import org.springframework.data.domain.Page;

public interface IYouthService {

    Boolean addYouth(YouthDTO youthDTO);

    Youth findYouthById(Long youthId);

    YouthDTO findYouthDtoById(Long youthId);

    Page<YouthMidLevelDTO> findAll(YouthFiltersDTO youthFiltersDTO);

    Boolean editYouth(YouthDTO youthDTO);
}