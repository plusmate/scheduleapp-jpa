package com.schedulegroup.scheduleapp.service.scheduleServ;

import com.schedulegroup.scheduleapp.entity.Schedule;
import com.schedulegroup.scheduleapp.entity.dto.ScheduleSaveDto;
import com.schedulegroup.scheduleapp.repository.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepo scheduleRepo;

    @Override
    public Long save(ScheduleSaveDto saveDto) {
        Schedule schedule = new Schedule(saveDto);
        Schedule saveEntity = scheduleRepo.save(schedule);
        Long id = saveEntity.getId();
        return id;
    }

    @Override
    public Schedule findById(Long id) {
        Optional<Schedule> optSchedule = scheduleRepo.findById(id);
        if (optSchedule.isPresent()) {
            Schedule schedule = optSchedule.get();
            return schedule;
        } else {
            throw new RuntimeException("해당하는 id의 일정이 존재하지 않습니다.");
        }
    }
}
