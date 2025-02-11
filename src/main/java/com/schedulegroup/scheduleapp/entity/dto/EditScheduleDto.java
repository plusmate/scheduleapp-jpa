package com.schedulegroup.scheduleapp.entity.dto;

import com.schedulegroup.scheduleapp.entity.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditScheduleDto {
    @NotNull
    private Long id;

    private String title;
    private String task;
}
