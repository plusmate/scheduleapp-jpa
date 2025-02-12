package com.schedulegroup.scheduleapp.controller;

import com.schedulegroup.scheduleapp.entity.Member;
import com.schedulegroup.scheduleapp.entity.dto.EditMemberDto;
import com.schedulegroup.scheduleapp.entity.dto.LoginDto;
import com.schedulegroup.scheduleapp.entity.dto.MemberDto;
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
    public ResponseEntity<MemberDto> login(@Valid @ModelAttribute LoginDto dto,
                                        HttpSession session) {
        Member loginMember = memberServ.loginCheck(dto);
        session.setAttribute("loginMember", loginMember);
        MemberDto memberDto = new MemberDto(loginMember);

        return ResponseEntity.ok().body(memberDto);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();

        return ResponseEntity.ok("로그아웃하였습니다.");
    }

    /**
     * 로그인한 멤버 정보 확인
     */
    @GetMapping("/search/")
    public ResponseEntity<MemberDto> searchMyInfo(HttpSession session) {
        Member loginMember = getLoginMember(session);
        MemberDto memberDto = new MemberDto(loginMember);

        return ResponseEntity.ok().body(memberDto);
    }

    private static Member getLoginMember(HttpSession session) {
        return (Member) session.getAttribute("loginMember");
    }

    @PostMapping("/edit")
    public ResponseEntity<MemberDto> editMember(@Valid @ModelAttribute EditMemberDto dto,
                                                HttpSession session) {
        MemberDto memberDto = memberServ.editMember(dto, getLoginMember(session));

        return ResponseEntity.ok().body(memberDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMember(HttpSession session) {
        Member loginMember = getLoginMember(session);
        memberServ.deleteMember(loginMember);

        return ResponseEntity.ok("회원 삭제 완료");
    }
}
