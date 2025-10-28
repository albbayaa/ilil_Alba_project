package com.ilil.alba.adapter.out.persistence;

import com.ilil.alba.application.member.port.out.MemberRepositoryPort;
import com.ilil.alba.domain.member.entity.Member;
import com.ilil.alba.adapter.out.persistence.member.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberJpaAdapter implements MemberRepositoryPort {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberJpaRepository.findById(id);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
    }

    @Override
    public List<Member> findAll() {
        return memberJpaRepository.findAll();
    }

    @Override
    public void delete(Member member) {
        memberJpaRepository.delete(member);
    }
}
