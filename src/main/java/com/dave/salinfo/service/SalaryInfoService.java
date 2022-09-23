package com.dave.salinfo.service;

import com.dave.salinfo.bean.SalaryListBean;
import com.dave.salinfo.repository.SalaryInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryInfoService {
  @Autowired private SalaryInfoRepository salaryRepository;

  public SalaryListBean getSalaryInfo() {
    salaryRepository.findAll();
    return new SalaryListBean();
  }
}
