package com.angle.youthmeeting.services;

import com.angle.youthmeeting.models.dtos.YouthDTO;
import com.angle.youthmeeting.models.dtos.YouthFiltersDTO;
import com.angle.youthmeeting.models.dtos.YouthMidLevelDTO;
import com.angle.youthmeeting.models.entities.Youth;
import org.springframework.data.domain.Page;

public interface IYouthService {

    Boolean addYouth(YouthDTO youthDTO);

    Youth findYouthById(Long youthId);

    YouthDTO findYouthDtoById(Long youthId);

    Page<YouthMidLevelDTO> findAll(YouthFiltersDTO youthFiltersDTO);

    Boolean editYouth(YouthDTO youthDTO);
}