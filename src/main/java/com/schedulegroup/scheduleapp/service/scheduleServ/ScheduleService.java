package com.schedulegroup.scheduleapp.service.scheduleServ;

import com.schedulegroup.scheduleapp.entity.Schedule;
import com.schedulegroup.scheduleapp.entity.dto.ScheduleSaveDto;

public interface ScheduleService {

    Long save(ScheduleSaveDto saveDto);

    Schedule findById(Long id);
}
