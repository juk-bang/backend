package com.jukbang.api.user.repository;

import com.jukbang.api.user.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByUnivId(int univId);
    //  List<User> findAllByUserId(String userid);
    // List<User> findAllById(long id);

}
