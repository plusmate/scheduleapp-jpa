package com.schedulegroup.scheduleapp.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 일정 저장 dto
 */
@Getter
@AllArgsConstructor
public class ScheduleSaveDto {
    @NotBlank
    private String name;

    @NotBlank
    private  String title;

    @NotBlank
    private  String task;
}
