package com.dave.salinfo.service;

import com.dave.salinfo.bean.SalaryInfoBean;
import com.dave.salinfo.bean.SalaryListBean;
import com.dave.salinfo.repository.SalaryInfoRepositoryImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryInfoService {
  @Autowired private SalaryInfoRepositoryImpl salaryRepository;

  public SalaryListBean getSalaryInfo(
      float min, float max, int offset, Integer limit, String sort) {
    List<SalaryInfoBean> salaryList;

    salaryList = salaryRepository.getSalaryInfoBySalaryBetween(min, max, offset, limit, sort);

    SalaryListBean response = new SalaryListBean();
    response.setResults(salaryList);
    return response;
  }
}
