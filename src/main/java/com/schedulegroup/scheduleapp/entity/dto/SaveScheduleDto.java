package com.schedulegroup.scheduleapp.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 일정 저장 dto
 */
@Getter
@AllArgsConstructor
public class SaveScheduleDto {
    @NotBlank
    @Size(max = 10, message = "제목은 최대 10글자까지 가능합니다.")
    private  String title;

    @NotBlank
    @Size(max = 50, message = "할 일은 최대 50자까지 가능합니다.")
    private  String task;
}
