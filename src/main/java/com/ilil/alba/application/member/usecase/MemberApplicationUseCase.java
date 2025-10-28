package com.ilil.alba.application.member.usecase;

import com.ilil.alba.application.member.port.out.MemberRepositoryPort;
import com.ilil.alba.domain.member.entity.Member;
import com.ilil.alba.application.member.port.in.MemberCommandPort;
import com.ilil.alba.application.member.port.in.MemberQueryPort;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.List;

@RequiredArgsConstructor
public class MemberApplicationUseCase implements MemberCommandPort, MemberQueryPort {

    private final MemberRepositoryPort memberRepositoryPort;

    @Override
    public Member createMember(Member member) {
        return memberRepositoryPort.save(member);
    }

    @Override
    public Member updateMember(Long id, Member member) {
        return memberRepositoryPort.findById(id)
                .map(existing -> memberRepositoryPort.save(member))
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    @Override
    public void deleteMember(Long id) {
        memberRepositoryPort.findById(id)
                .ifPresent(memberRepositoryPort::delete);
    }

    @Override
    public Optional<Member> getMemberById(Long id) {
        return memberRepositoryPort.findById(id);
    }

    @Override
    public Optional<Member> getMemberByEmail(String email) {
        return memberRepositoryPort.findByEmail(email);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepositoryPort.findAll();
    }
}
