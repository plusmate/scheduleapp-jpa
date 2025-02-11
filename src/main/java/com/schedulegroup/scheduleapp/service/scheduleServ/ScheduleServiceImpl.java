package com.schedulegroup.scheduleapp.service.scheduleServ;

import com.schedulegroup.scheduleapp.entity.Member;
import com.schedulegroup.scheduleapp.entity.Schedule;
import com.schedulegroup.scheduleapp.entity.dto.EditScheduleDto;
import com.schedulegroup.scheduleapp.entity.dto.SaveScheduleDto;
import com.schedulegroup.scheduleapp.repository.ScheduleRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepo scheduleRepo;

    public List<Schedule> findAll() {
        return scheduleRepo.findAll();
    }

    @Override
    public Long save(SaveScheduleDto saveDto) {
        Member member = new Member(saveDto.getName());
        Schedule schedule = new Schedule(saveDto, member);
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

    /**
     * Member엔티티 변경 / 삭제시, schedule엔티티의 member도 변경 삭제
     * @param isDelete // true : member엔티티 변경 로직 / false memeber엔티티 삭제 로직
     */
    @Override
    public void syncMember(Member member, boolean isDelete) {
        for (Schedule schedule : findAll()) {
            if (schedule.getMember().equals(member)) {
                if (!isDelete) {
                    schedule.setMember(member);
                } else {
                    Long id = schedule.getId();
                    scheduleRepo.deleteById(id);
                }
            }
        }
    }


}
