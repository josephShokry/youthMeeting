package com.example.demo.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MeetingDTO extends BasicEntityDTO{

    @JsonProperty("name")
    private String name;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("date")
    private String date;

    @JsonProperty("instructorId")
    private Long instructorId;
}
