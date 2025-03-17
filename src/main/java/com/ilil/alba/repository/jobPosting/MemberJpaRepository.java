package com.ilil.alba.repository.jobPosting;

import com.ilil.alba.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m FROM Member m JOIN FETCH m.emailVerification ev " +
                  "WHERE ev.email = :email")
    Optional<Member> findByEmail(String email);
}
