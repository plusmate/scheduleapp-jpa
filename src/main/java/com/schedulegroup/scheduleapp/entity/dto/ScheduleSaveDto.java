package com.schedulegroup.scheduleapp.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 일정 저장 dto
 */
@Getter
@AllArgsConstructor
public class ScheduleSaveDto {
    private String name;
    private String title;
    private String task;
}
