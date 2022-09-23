package com.dave.salinfo.controller;

import com.dave.salinfo.Constants;
import com.dave.salinfo.bean.SalaryListBean;
import com.dave.salinfo.bean.SalaryResponseBean;
import com.dave.salinfo.factory.FileProcessingFactory;
import com.dave.salinfo.service.SalaryInfoService;
import com.sun.jdi.InvalidTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
public class SalaryController {
  @Autowired private SalaryInfoService salaryInfoService;
  @Autowired private FileProcessingFactory fileProcessingFactory;

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
  public ResponseEntity<SalaryResponseBean> uploadSalary(@RequestParam("file") MultipartFile file)
      throws InvalidTypeException {
    if (Objects.isNull(file) || Objects.isNull(file.getContentType())) {
      throw new InvalidTypeException(Constants.INVALID_FILE_TYPE);
    }
    var processor = fileProcessingFactory.getProcessor(file.getContentType());
    processor.processSalaryFile(file);
    return ResponseEntity.ok(new SalaryResponseBean());
  }
}
