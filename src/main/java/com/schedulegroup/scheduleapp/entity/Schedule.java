package com.schedulegroup.scheduleapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schedulegroup.scheduleapp.entity.dto.EditScheduleDto;
import com.schedulegroup.scheduleapp.entity.dto.ScheduleSaveDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
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
    private String name;

    @NotBlank
    private  String title;

    @NotBlank
    private  String task;

    /* 현재 시간을 초를 제외한 포멧으로 저장 */
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm")
    private LocalDateTime modifiedDate;

    public Schedule() {

    }

    public Schedule(ScheduleSaveDto saveDto) {
        this.name = saveDto.getName();
        this.task = saveDto.getTask();
        this.title = saveDto.getTitle();
    }

    public Schedule editSchedule(EditScheduleDto editDto) {
        this.name = editDto.getName();
        this.title = editDto.getTitle();
        this.task = editDto.getTask();

        return this;
    }
}