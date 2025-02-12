package com.schedulegroup.scheduleapp.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditMemberDto {
    @Email
    private String email;

    @Size(max = 6, message = "이름은 최대6글자까지 가능합니다.")
    private String name;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "비밀번호는 영문자와 숫자를 포함하여 8자 이상이어야 합니다.")
    private String password;
}
