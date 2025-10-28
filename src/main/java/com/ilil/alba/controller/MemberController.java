package com.ilil.alba.controller;

import com.ilil.alba.domain.member.entity.Member;
import com.ilil.alba.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public ResponseEntity<List<Member>> getAllMember(){
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

}
