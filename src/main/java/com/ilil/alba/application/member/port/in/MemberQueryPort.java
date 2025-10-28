package com.ilil.alba.application.member.port.in;

import com.ilil.alba.domain.member.entity.Member;
import java.util.Optional;
import java.util.List;

public interface MemberQueryPort {
    Optional<Member> getMemberById(Long id);
    Optional<Member> getMemberByEmail(String email);
    List<Member> getAllMembers();
}
