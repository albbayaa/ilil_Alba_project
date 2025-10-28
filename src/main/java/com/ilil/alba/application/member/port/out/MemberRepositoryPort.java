package com.ilil.alba.application.member.port.out;

import com.ilil.alba.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryPort {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);
    List<Member> findAll();
    void delete(Member member);
}
