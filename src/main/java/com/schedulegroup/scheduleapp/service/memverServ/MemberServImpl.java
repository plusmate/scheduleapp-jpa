package com.schedulegroup.scheduleapp.service.memverServ;

import com.schedulegroup.scheduleapp.entity.Member;
import com.schedulegroup.scheduleapp.entity.dto.EditMemberDto;
import com.schedulegroup.scheduleapp.entity.dto.LoginDto;
import com.schedulegroup.scheduleapp.entity.dto.SaveMemberDto;
import com.schedulegroup.scheduleapp.repository.MemberRepo;
import com.schedulegroup.scheduleapp.service.scheduleServ.ScheduleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServImpl implements MemberServ {

    private final MemberRepo memberRepo;
    private final ScheduleService scheduleServ;

    @Override
    public Long save(SaveMemberDto dto) {
        Member member = new Member(dto);

        Member save = memberRepo.save(member);
        Long id = save.getId();
        return id;
    }

    @Override
    public Member findById(Long id) {
        Optional<Member> optMember = memberRepo.findById(id);
        if (optMember.isPresent()) {
            return optMember.get();
        } else {
            throw new RuntimeException("번 일정은 존재하지 않습니다.");
        }
    }

    @Override
    public Long editMember(Long id, EditMemberDto dto) {
        Member member = findById(id);
        member.editMember(dto);
        scheduleServ.syncMember(member, false);

        return id;
    }

    @Override
    public void deleteMember(Long id) {
        Member member = findById(id);

        scheduleServ.syncMember(member, true);
        memberRepo.deleteById(id);
    }

    @Override
    public Member loginCheck(LoginDto dto) {
        for (Member member : memberRepo.findAll()) {
            if (member.getEmail().equals(dto.getEmail()) &&
                    member.getPassword().equals(dto.getPassword())) {
                return member;
            }
        }
        return null;
    }
}
