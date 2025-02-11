package com.schedulegroup.scheduleapp.service.memverServ;

import com.schedulegroup.scheduleapp.entity.Member;
import com.schedulegroup.scheduleapp.entity.dto.EditMemberDto;
import com.schedulegroup.scheduleapp.entity.dto.SaveMemberDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface MemberServ {

    Long save(@Valid @ModelAttribute SaveMemberDto dto);

    Member findById(Long id);

    Long editMember(Long id, EditMemberDto dto);

    void deleteMember(Long id);
}
