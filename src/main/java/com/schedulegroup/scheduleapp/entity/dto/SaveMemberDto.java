package com.schedulegroup.scheduleapp.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SaveMemberDto {
    @NotBlank
    private String name;
}
