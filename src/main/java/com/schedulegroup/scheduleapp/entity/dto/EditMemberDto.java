package com.schedulegroup.scheduleapp.entity.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditMemberDto {
    @Email
    private String email;

    private String name;
}
