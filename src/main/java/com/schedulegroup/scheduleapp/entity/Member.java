package com.schedulegroup.scheduleapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schedulegroup.scheduleapp.entity.dto.SaveMemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    /* 현재 시간을 초를 제외한 포멧으로 저장 */
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm")
    private LocalDateTime modifiedDate;

    public Member(SaveMemberDto dto) {
        this.name = dto.getName();
    }

    public Member(String name) {
        this.name = name;
    }
}
