package com.schedulegroup.scheduleapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schedulegroup.scheduleapp.entity.dto.EditScheduleDto;
import com.schedulegroup.scheduleapp.entity.dto.SaveScheduleDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 일정 데이터 엔티티
 */
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private  String title;

    @NotBlank
    private  String task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;

    /* 현재 시간을 초를 제외한 포멧으로 저장 */
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm")
    private LocalDateTime createdDate;


    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm")
    private LocalDateTime modifiedDate;

    public Schedule() {

    }

    public Schedule(SaveScheduleDto saveDto, Member member) {
        this.task = saveDto.getTask();
        this.title = saveDto.getTitle();
        this.member = member;
    }

    public Schedule editSchedule(EditScheduleDto editDto) {
        this.title = editDto.getTitle();
        this.task = editDto.getTask();

        return this;
    }
}