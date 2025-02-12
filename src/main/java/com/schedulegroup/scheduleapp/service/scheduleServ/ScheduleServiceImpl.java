package com.schedulegroup.scheduleapp.service.scheduleServ;

import com.schedulegroup.scheduleapp.entity.Member;
import com.schedulegroup.scheduleapp.entity.Schedule;
import com.schedulegroup.scheduleapp.entity.dto.EditScheduleDto;
import com.schedulegroup.scheduleapp.entity.dto.SaveScheduleDto;
import com.schedulegroup.scheduleapp.entity.dto.FindByDateDto;
import com.schedulegroup.scheduleapp.entity.dto.ScheduleDto;
import com.schedulegroup.scheduleapp.repository.ScheduleRepo;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public Long save(SaveScheduleDto saveDto, Member member) {
        Schedule schedule = new Schedule(saveDto, member);
        Schedule saveEntity = scheduleRepo.save(schedule);
        Long id = saveEntity.getId();
        return id;
    }

    @Override
    public List<Schedule> findByDate(FindByDateDto dto, Member member) {
        List<Schedule> schedules = findByMember(member);
        ArrayList<Schedule> resultList = new ArrayList<>();
        for (Schedule schedule : schedules) {
            LocalDate targetDate = schedule.getCreatedDate().toLocalDate();
            LocalDate startDate = dto.getStartDate();
            LocalDate endDate = dto.getEndDate();

            if (targetDate.isAfter(startDate) && targetDate.isBefore(endDate)) {
                resultList.add(schedule);
            }
        }
        return resultList;
    }

    private static Member getLoginMember(HttpSession session) {
        return (Member) session.getAttribute("loginMember");
    }

    @Override
    public List<ScheduleDto> findMySchedule(Member member) {
        List<ScheduleDto> schedules = new ArrayList<>();
        for (Schedule schedule : findByMember(member)) {
            ScheduleDto scheduleDto = new ScheduleDto(schedule);
            schedules.add(scheduleDto);
        }
        return schedules;
    }

    @Override
    public void deleteById(Long id, Member member) {
        boolean checkId = false;
        List<Schedule> schedules = findByMember(member);

        for (Schedule schedule : schedules) {
            Long myId = schedule.getId();
            if (id.equals(myId)) {
                scheduleRepo.deleteById(id);
                break;
            }
        }
        throw new RuntimeException("해당하는 일정이 없습니다.");
    }

    @Override
    public ScheduleDto editSchedule(Long id, EditScheduleDto dto, Member member) {
        for (Schedule schedule : findByMember(member)) {
            if (id.equals(schedule.getId())) {
                schedule.editSchedule(dto);
                ScheduleDto scheduleDto = new ScheduleDto(schedule);
                return scheduleDto;
            }
        }
        throw new RuntimeException("해당하는 일정이 없습니다.");
    }

    /**
     * Member엔티티 변경 / 삭제시, schedule엔티티의 member도 변경 삭제
     * @param isDelete // true : member엔티티 변경 로직 / false memeber엔티티 삭제 로직
     */
    @Override
    public void syncMember(Member member, boolean isDelete) {

        List<Schedule> schedules = findByMember(member);
        for (Schedule schedule : schedules) {
            if (isDelete) {
                Long id = schedule.getId();
                scheduleRepo.deleteById(id);
            } else {
                schedule.setMember(member);
            }
        }
    }

    /**
     * 로그인한 멤버의 일정 정보 반환
     */
    private List<Schedule> findByMember(Member member) {
        ArrayList<Schedule> schedules = new ArrayList<>();
        List<Schedule> allSchedule = scheduleRepo.findAll();

        for (Schedule schedule : allSchedule) {
            if (schedule.getMember().getId().equals(member.getId())) {
                schedules.add(schedule);
            }
        }
        if (schedules.isEmpty()) {
            throw new RuntimeException("일정이 존재하지 않습니다");
        }
        return schedules;
    }

    private Schedule findById(Long id) {
        Optional<Schedule> optSchedule = scheduleRepo.findById(id);
        if (optSchedule.isPresent()) {
            Schedule schedule = optSchedule.get();
            return schedule;
        } else {
            throw new RuntimeException(id + "번 일정은 존재하지 않습니다.");
        }
    }
}
