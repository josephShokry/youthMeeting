package com.angel.youthmeeting.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDTO extends BasicEntityDTO{

    private Long fatherId;

    private Long servantId;
}
