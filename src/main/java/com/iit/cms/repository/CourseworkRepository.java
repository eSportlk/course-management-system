package com.iit.cms.repository;

import com.iit.cms.model.Coursework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseworkRepository extends JpaRepository<Coursework, Long> {
}
