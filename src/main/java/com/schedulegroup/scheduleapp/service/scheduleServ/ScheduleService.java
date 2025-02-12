package com.schedulegroup.scheduleapp.service.scheduleServ;

import com.schedulegroup.scheduleapp.entity.Member;
import com.schedulegroup.scheduleapp.entity.Schedule;
import com.schedulegroup.scheduleapp.entity.dto.EditScheduleDto;
import com.schedulegroup.scheduleapp.entity.dto.SaveScheduleDto;
import com.schedulegroup.scheduleapp.entity.dto.FindByDateDto;
import com.schedulegroup.scheduleapp.entity.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {

    Long save(SaveScheduleDto saveDto, Member member);

    List<Schedule> findByDate(FindByDateDto dto, Member member);

    List<ScheduleDto> findMySchedule(Member member);

    void deleteById(Long id, Member member);

    ScheduleDto editSchedule(Long id, EditScheduleDto dto, Member member);

    void syncMember(Member member, boolean isDelete);
}
