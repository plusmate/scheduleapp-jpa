package com.schedulegroup.scheduleapp.service.memverServ;

import com.schedulegroup.scheduleapp.entity.Member;
import com.schedulegroup.scheduleapp.entity.dto.EditMemberDto;
import com.schedulegroup.scheduleapp.entity.dto.LoginDto;
import com.schedulegroup.scheduleapp.entity.dto.MemberDto;
import com.schedulegroup.scheduleapp.entity.dto.SaveMemberDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface MemberServ {

    Long save(@Valid @ModelAttribute SaveMemberDto dto);

    MemberDto editMember(EditMemberDto dto, Member member);

    void deleteMember(Member member);

    Member loginCheck(LoginDto dto);
}
