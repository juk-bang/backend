package com.jukbang.api.admin.repository;


import com.jukbang.api.admin.entity.PostReport;
import com.jukbang.api.community.CommunityRole;
import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostReportRepository extends JpaRepository<PostReport,Long> {
    List<PostReport> findAllByRole(CommunityRole role);
    Optional<PostReport> findByRoleAndPostReportId(CommunityRole role, Long postReportId);

}
