package com.schedulegroup.scheduleapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schedulegroup.scheduleapp.entity.dto.EditMemberDto;
import com.schedulegroup.scheduleapp.entity.dto.SaveMemberDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    /* 현재 시간을 초를 제외한 포멧으로 저장 */
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm")
    private LocalDateTime modifiedDate;

    public Member(SaveMemberDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
    }

    public Member(String name) {
        this.name = name;
    }

    public void editMember(EditMemberDto dto) {
        if (dto.getName() != null) {
            this.name = dto.getName();
        } else if (dto.getEmail() != null) {
            this.email = dto.getEmail();
        } else if (dto.getPassword() != null) {
            this.password = dto.getPassword();
        }
    }
}
