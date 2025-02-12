package com.schedulegroup.scheduleapp.entity.dto;

import com.schedulegroup.scheduleapp.entity.Member;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditScheduleDto {

    @Size(max = 10, message = "제목은 최대 10글자까지 가능합니다.")
    private String title;

    @Size(max = 50, message = "할 일은 최대 50자까지 가능합니다.")
    private String task;
}
