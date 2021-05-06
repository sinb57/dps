package com.project.dps.repository;

import com.project.dps.domain.poc.PocTestCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PocRepository extends JpaRepository<PocTestCategory, Long> {




}
