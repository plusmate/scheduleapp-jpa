package com.schedulegroup.scheduleapp.controller;

import com.schedulegroup.scheduleapp.entity.Schedule;
import com.schedulegroup.scheduleapp.entity.dto.EditScheduleDto;
import com.schedulegroup.scheduleapp.entity.dto.SaveScheduleDto;
import com.schedulegroup.scheduleapp.service.scheduleServ.ScheduleService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleServ;

    /**
     * dto를 이용한 일정 생성
     */
    @PostMapping("/new-schedule")
    public ResponseEntity<String> makeNewSchedule(@Valid @ModelAttribute SaveScheduleDto saveDto) {
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

    /**
     * id를 이용한 일정 삭제
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteSchedule(@RequestParam Long id) {
        scheduleServ.deleteById(id);


        return ResponseEntity.ok(id + "번 일정을 삭제했습니다");
    }

    @PostMapping("/edit")
    public ResponseEntity<Schedule> editSchedule(@Valid @ModelAttribute EditScheduleDto editScheduleDto) {
        Schedule schedule = scheduleServ.editSchedule(editScheduleDto);

        return ResponseEntity.ok().body(schedule);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> typeMissException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 파라미터 : 알맞은 타입의 파라미터를 입력하세요.");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> blankException(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 파라미터 :  파라미터 값이 누락되었습니다.");
    }
}

