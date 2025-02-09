package com.schedulegroup.scheduleapp.controller;

import com.schedulegroup.scheduleapp.entity.Schedule;
import com.schedulegroup.scheduleapp.entity.dto.ScheduleSaveDto;
import com.schedulegroup.scheduleapp.service.scheduleServ.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleServ;

    /**
     * dto를 이용한 일정 생성
     */
    @PostMapping("/new-schedule")
    public ResponseEntity<String> makeNewSchedule(@ModelAttribute ScheduleSaveDto saveDto) {
        Long savedId = scheduleServ.save(saveDto);

        return ResponseEntity.ok(savedId + "번 일정 생성 완료");
    }

    /**
     * id를 이용한 일정 검색
     */
    @GetMapping("/search")
    public ResponseEntity<Schedule> searchSchedule(@RequestParam Long id) {
        Schedule schedule = scheduleServ.findById(id);

        return ResponseEntity.ok().body(schedule);
    }
}
