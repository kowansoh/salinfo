package com.dave.salinfo.processor;

import com.dave.salinfo.repository.SalaryInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Lazy
@Component
public class SalaryCSVProcessor implements SalaryFileProcessor {
  @Autowired private SalaryInfoRepository salaryRepository;

  @Override
  public void processSalaryFile(MultipartFile file) {
    // STUB
  }
}
