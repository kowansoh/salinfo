package com.dave.salinfo.repository;

import com.dave.salinfo.entity.SalaryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryInfoRepository extends JpaRepository<SalaryInfo, Long> {

}
