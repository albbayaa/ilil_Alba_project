package com.ilil.alba.adapter.out.persistence.member;

import com.ilil.alba.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m JOIN FETCH m.emailVerification ev " +
            "WHERE ev.email = :email")
    Optional<Member> findByEmail(String email);
}
