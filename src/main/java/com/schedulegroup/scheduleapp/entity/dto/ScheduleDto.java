package com.schedulegroup.scheduleapp.entity.dto;

import com.schedulegroup.scheduleapp.entity.Schedule;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleDto {
    private Long id;
    private String title;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String name;

    public ScheduleDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.createdDate = schedule.getCreatedDate();
        this.modifiedDate = schedule.getModifiedDate();
        this.name = schedule.getMember().getName();
    }
}
