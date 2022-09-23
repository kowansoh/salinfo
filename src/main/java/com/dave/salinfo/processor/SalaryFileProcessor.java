package com.dave.salinfo.processor;

import org.springframework.web.multipart.MultipartFile;

public interface SalaryFileProcessor {

  void processSalaryFile(MultipartFile file);
}
