package com.schedulegroup.scheduleapp.service.scheduleServ;

import com.schedulegroup.scheduleapp.entity.Schedule;
import com.schedulegroup.scheduleapp.entity.dto.EditScheduleDto;
import com.schedulegroup.scheduleapp.entity.dto.ScheduleSaveDto;

public interface ScheduleService {

    Long save(ScheduleSaveDto saveDto);

    Schedule findById(Long id);

    void deleteById(Long id);

    Schedule editSchedule(EditScheduleDto dto);
}
