package com.schedulegroup.scheduleapp.controller;

import com.schedulegroup.scheduleapp.entity.Member;
import com.schedulegroup.scheduleapp.entity.dto.EditMemberDto;
import com.schedulegroup.scheduleapp.entity.dto.LoginDto;
import com.schedulegroup.scheduleapp.entity.dto.SaveMemberDto;
import com.schedulegroup.scheduleapp.service.memverServ.MemberServ;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberServ memberServ;

    @PostMapping("/new-member")
    public ResponseEntity<String> newMember(@Valid @ModelAttribute SaveMemberDto dto) {
        Long savedNumber = memberServ.save(dto);

        return ResponseEntity.ok(savedNumber + "번 회원 생성 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<Member> login(@Valid @ModelAttribute LoginDto dto,
                                        HttpSession session) {
        Member loginMember = memberServ.loginCheck(dto);
        session.setAttribute("loginMember", loginMember);
        return ResponseEntity.ok().body(loginMember);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Member> searchMemberById(@PathVariable Long id) {
        Member findedMember = memberServ.findById(id);

        return ResponseEntity.ok().body(findedMember);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<String> editMember(@PathVariable Long id,
                                             @Valid @ModelAttribute EditMemberDto dto) {
        Long targetId = memberServ.editMember(id, dto);

        return ResponseEntity.ok(targetId + "번 회원 수정 완료");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberServ.deleteMember(id);

        return ResponseEntity.ok(id + "번 회원 삭제 완료");
    }
}
