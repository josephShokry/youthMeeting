package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.YouthIntermediateDTO;
import com.example.demo.models.DTOs.YouthLightDTO;
import com.example.demo.models.Youth;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YouthIntermediateMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "familyId", source = "family.id")
    YouthIntermediateDTO youthToYouthIntermediateDto(Youth youth, @MappingTarget YouthIntermediateDTO youthIntermediateDTO);

    default Iterable<YouthIntermediateDTO> youthsToYouthIntermediateDtos(Iterable<Youth> youths){
        return StreamSupport.stream(youths.spliterator(), false)
                .map(entity -> youthToYouthIntermediateDto(entity,new YouthIntermediateDTO()))
                .collect(Collectors.toList());
    }
    default Page<YouthIntermediateDTO> youthsToPageYouthIntermediateDtos(Page<Youth> YouthsPage) {
        List<YouthIntermediateDTO> dtoList = (List<YouthIntermediateDTO>) youthsToYouthIntermediateDtos(YouthsPage.getContent());
        return new PageImpl<>(dtoList, YouthsPage.getPageable(), YouthsPage.getTotalElements());
    }
}
