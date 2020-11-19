package com.jukbang.api.admin.repository;


import com.jukbang.api.admin.entity.PostReport;
import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReportRepository extends JpaRepository<PostReport,Long> {
}
