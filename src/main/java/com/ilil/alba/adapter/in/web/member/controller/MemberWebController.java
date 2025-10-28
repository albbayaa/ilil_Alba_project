package com.ilil.alba.adapter.in.web.member.controller;

import com.ilil.alba.application.member.port.in.MemberCommandPort;
import com.ilil.alba.application.member.port.in.MemberQueryPort;
import com.ilil.alba.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberWebController {

    private final MemberQueryPort memberQueryPort;
    private final MemberCommandPort memberCommandPort;

    @GetMapping
    public BaseResponse<?> getAllMembers() {
        return new BaseResponse<>(memberQueryPort.getAllMembers());
    }

    @GetMapping("/{id}")
    public BaseResponse<?> getMember(@PathVariable Long id) {
        return new BaseResponse<>(memberQueryPort.getMemberById(id)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다.")));
    }
}
