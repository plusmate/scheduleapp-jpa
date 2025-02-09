package com.schedulegroup.scheduleapp.repository;

import com.schedulegroup.scheduleapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
}
