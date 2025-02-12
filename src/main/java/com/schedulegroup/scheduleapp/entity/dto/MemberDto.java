package com.schedulegroup.scheduleapp.entity.dto;

import com.schedulegroup.scheduleapp.entity.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MemberDto(Member member) {
        this.createdDate = member.getCreatedDate();
        this.email = member.getEmail();
        this.modifiedDate = member.getModifiedDate();
        this.name = member.getName();
        this.password = member.getPassword();
    }
}
