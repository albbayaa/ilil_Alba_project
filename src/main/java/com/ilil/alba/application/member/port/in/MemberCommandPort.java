package com.ilil.alba.application.member.port.in;

import com.ilil.alba.domain.member.entity.Member;

public interface MemberCommandPort {
    Member createMember(Member member);
    Member updateMember(Long id, Member member);
    void deleteMember(Long id);
}
