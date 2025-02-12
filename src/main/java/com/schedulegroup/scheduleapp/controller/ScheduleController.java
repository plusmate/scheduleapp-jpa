package com.schedulegroup.scheduleapp.controller;

import com.schedulegroup.scheduleapp.entity.Member;
import com.schedulegroup.scheduleapp.entity.Schedule;
import com.schedulegroup.scheduleapp.entity.dto.EditScheduleDto;
import com.schedulegroup.scheduleapp.entity.dto.SaveScheduleDto;
import com.schedulegroup.scheduleapp.entity.dto.FindByDateDto;
import com.schedulegroup.scheduleapp.entity.dto.ScheduleDto;
import com.schedulegroup.scheduleapp.service.scheduleServ.ScheduleService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleServ;

    /**
     * dto를 이용한 일정 생성
     */
    @PostMapping("/new-schedule")
    public ResponseEntity<String> makeNewSchedule(@Valid @ModelAttribute SaveScheduleDto saveDto,
                                                  HttpSession session) {
        Member loginMember = getLoginMember(session);
        Long savedId = scheduleServ.save(saveDto, loginMember);


        return ResponseEntity.ok(savedId + "번 일정 생성 완료");
    }

    /**
     * 특정 날짜 사이에 있는 일정 반환
     */
    @PostMapping("/search/date")
    public ResponseEntity<List<ScheduleDto>> findByDate(@Valid @ModelAttribute FindByDateDto dto, HttpSession session) {
        dto.checkDate();
        ArrayList<ScheduleDto> scheduleDtoList = new ArrayList<>();
        Member loginMember = getLoginMember(session);
        List<Schedule> resultSchedule = scheduleServ.findByDate(dto, loginMember);
        for (Schedule schedule : resultSchedule) {
            scheduleDtoList.add(new ScheduleDto(schedule));
        }
        return ResponseEntity.ok().body(scheduleDtoList);
    }

    /**
     * id를 이용한 일정 검색
     */
    @GetMapping("/search")
    public ResponseEntity<List<ScheduleDto>> searchSchedule(HttpSession session) {
        List<ScheduleDto> schedules = scheduleServ.findMySchedule(getLoginMember(session));

        return ResponseEntity.ok().body(schedules);
    }

    /**
     * id를 이용한 일정 변경
     * 본인 계정의 일정만 변경 가능
     */
    @PostMapping("/edit/{id}")
    public ResponseEntity<ScheduleDto> editSchedule(@Valid @ModelAttribute EditScheduleDto editScheduleDto,
                                                    @PathVariable Long id,
                                                    HttpSession session,
                                                    HttpServletResponse resp) throws IOException {
        ScheduleDto schedule = scheduleServ.editSchedule(id, editScheduleDto, getLoginMember(session));

        return ResponseEntity.ok().body(schedule);
    }

    /**
     * id를 이용한 일정 삭제
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, HttpSession session) {
        scheduleServ.deleteById(id, getLoginMember(session));

        return ResponseEntity.ok(id + "번 일정을 삭제했습니다");
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

    private static Member getLoginMember(HttpSession session) {
        return (Member) session.getAttribute("loginMember");
    }
}

