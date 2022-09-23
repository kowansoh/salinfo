package com.dave.salinfo.controller;

import com.dave.salinfo.bean.SalaryListBean;
import com.dave.salinfo.service.SalaryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {
  @Autowired private SalaryInfoService salaryInfoService;

  @GetMapping("/users")
  public ResponseEntity<SalaryListBean> getSalary(
      @RequestParam(defaultValue = "0.0") float min,
      @RequestParam(defaultValue = "4000.0") float max,
      @RequestParam(defaultValue = "0") int offset,
      @RequestParam(required = false) Integer limit,
      @RequestParam(defaultValue = "name") String sort) {
    return ResponseEntity.ok().body(salaryInfoService.getSalaryInfo());
  }

  @PostMapping("/upload")
  public ResponseEntity<?> uploadSalary() {
    return ResponseEntity.ok().build();
  }
}
