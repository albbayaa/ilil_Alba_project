package com.ilil.alba.service;

import com.ilil.alba.domain.member.entity.Member;
import com.ilil.alba.repository.member.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberRepository;

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

}
