package com.ilil.alba.repository.jobPosting;


import com.ilil.alba.domain.QJobPosting;
import com.ilil.alba.domain.base.BaseStatus;
import com.ilil.alba.dto.JobPostingSearchRequest;
import com.ilil.alba.dto.JobPostingSearchResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobPostingRepository implements JobPostingDslRepository{
    private final JPAQueryFactory queryFactory;
    QJobPosting jobPosting = QJobPosting.jobPosting;

    public List<JobPostingSearchResponse.SearchResults> searchJobPostings(JobPostingSearchRequest request,Long lastId,int limit) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(jobPosting.status.eq(BaseStatus.ACTIVE));

        if (lastId != null) {
            builder.and(jobPosting.jobPostingId.lt(lastId));
        }

        if(request.getTitle() != null){
            builder.and(jobPosting.title.containsIgnoreCase(request.getTitle()));
        }

        if(request.getLocation() != null){
            builder.and(jobPosting.location.containsIgnoreCase(request.getLocation()));
        }

        if (request.getWorkDate() != null) {
            builder.and(jobPosting.workDate.eq(request.getWorkDate()));
        }

        if (request.isOneDayJob()) {
            builder.and(jobPosting.isOneDayJob.isTrue());
        }

        var projections = Projections.fields(JobPostingSearchResponse.SearchResults.class,
                jobPosting.jobPostingId,
                jobPosting.title,
                jobPosting.location,
                jobPosting.workDate,
                jobPosting.dailyWage,
                jobPosting.paymentDate,
                jobPosting.isOneDayJob);

        return queryFactory
                .from(jobPosting)
                .select(projections)
                .where(builder)
                .orderBy(jobPosting.jobPostingId.desc())
                .limit(limit)
                .fetch();

    }

}
