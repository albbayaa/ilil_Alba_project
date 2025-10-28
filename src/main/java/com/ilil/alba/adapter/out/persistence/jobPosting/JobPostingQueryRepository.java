package com.ilil.alba.adapter.out.persistence.jobPosting;

import com.ilil.alba.domain.jobPosting.entity.QJobPosting;
import com.ilil.alba.domain.base.BaseStatus;
import com.ilil.alba.adapter.in.web.jobPosting.response.JobPostingSearchResponse;
import com.ilil.alba.adapter.in.web.jobPosting.request.JobPostingSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobPostingQueryRepository implements JobPostingDslRepository {

    private final JPAQueryFactory queryFactory;
    private final QJobPosting jobPosting = QJobPosting.jobPosting;

    public List<JobPostingSearchResponse.SearchResults> searchJobPostings(JobPostingSearchRequest request, Long lastId, int limit) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(jobPosting.status.eq(BaseStatus.ACTIVE));

        if (lastId != null) {
            builder.and(jobPosting.jobPostingId.lt(lastId));
        }
        if (request.getTitle() != null) {
            builder.and(jobPosting.title.containsIgnoreCase(request.getTitle()));
        }
        if (request.getLocation() != null) {
            builder.and(jobPosting.location.containsIgnoreCase(request.getLocation()));
        }
        if (request.getWorkDate() != null) {
            builder.and(jobPosting.workDate.eq(request.getWorkDate()));
        }
        if (request.getIsOneDayJob() != null) {
            builder.and(jobPosting.isOneDayJob.eq(request.getIsOneDayJob()));
        }

        var projections = Projections.fields(
                JobPostingSearchResponse.SearchResults.class,
                jobPosting.jobPostingId,
                jobPosting.title,
                jobPosting.location,
                jobPosting.workDate,
                jobPosting.dailyWage,
                jobPosting.paymentDate,
                jobPosting.isOneDayJob
        );

        return queryFactory
                .select(projections)
                .from(jobPosting)
                .where(builder)
                .orderBy(jobPosting.jobPostingId.desc())
                .limit(limit)
                .fetch();
    }
}
