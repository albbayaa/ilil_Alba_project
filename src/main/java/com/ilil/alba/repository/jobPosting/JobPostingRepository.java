package com.ilil.alba.repository.jobPosting;


import com.ilil.alba.domain.QJobPosting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobPostingRepository implements JobPostingDslRepository{
    private final JPAQueryFactory queryFactory;
    QJobPosting jobPosting = QJobPosting.jobPosting;



}
