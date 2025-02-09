package com.schedulegroup.scheduleapp.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditScheduleDto {
    @NotNull
    private Long id;

    private String name;
    private String title;
    private String task;
}
