package com.schedulegroup.scheduleapp.service.scheduleServ;

import com.schedulegroup.scheduleapp.entity.Schedule;
import com.schedulegroup.scheduleapp.entity.dto.EditScheduleDto;
import com.schedulegroup.scheduleapp.entity.dto.ScheduleSaveDto;
import com.schedulegroup.scheduleapp.repository.ScheduleRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
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
            throw new RuntimeException(id + "번 일정은 존재하지 않습니다.");
        }
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        scheduleRepo.deleteById(id);
    }

    @Override
    public Schedule editSchedule(EditScheduleDto dto) {
        Long id = dto.getId();
        Schedule schedule = this.findById(id);
        return schedule.editSchedule(dto);
    }
}
