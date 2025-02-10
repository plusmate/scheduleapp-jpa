package com.schedulegroup.scheduleapp.service.memverServ;

import com.schedulegroup.scheduleapp.entity.Member;
import com.schedulegroup.scheduleapp.entity.dto.SaveMemberDto;
import com.schedulegroup.scheduleapp.repository.MemberRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
@RequiredArgsConstructor
public class MemberServImpl implements MemberServ {

    private final MemberRepo memberRepo;

    public void save(@Valid @ModelAttribute SaveMemberDto dto) {
        Member member = new Member(dto);

        memberRepo.save(member);
    }
}
