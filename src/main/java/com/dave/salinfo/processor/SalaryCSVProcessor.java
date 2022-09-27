package com.dave.salinfo.processor;

import com.dave.salinfo.Constants;
import com.dave.salinfo.entity.SalaryInfo;
import com.dave.salinfo.repository.SalaryInfoRepository;
import com.dave.salinfo.utils.CsvUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVParser;
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
    try {
      CSVParser csvParser = CsvUtils.readCsv(file.getInputStream());
      validateCsvFile(csvParser.getHeaderMap());
      List<SalaryInfo> salaryInfos = new ArrayList<>();
      csvParser.stream()
          .iterator()
          .forEachRemaining(
              csvRecord -> {
                float salary = Float.parseFloat(csvRecord.get(Constants.SALARY_COLUMN_NAME));
                if (salary >= 0f) {
                  SalaryInfo salaryInfo =
                      new SalaryInfo(csvRecord.get(Constants.NAME_COLUMN_NAME).trim(), salary);
                  salaryInfos.add(salaryInfo);
                }
              });
      if (!salaryInfos.isEmpty()) {
        salaryRepository.saveAll(salaryInfos);
      }
    } catch (IOException e) {
      throw new IllegalStateException("fail to parse csv data: " + e.getMessage());
    }
  }

  private void validateCsvFile(Map<String, Integer> headers) {
    if (headers.size() != Constants.CSV_HEADERS.length) {
      throw new IllegalArgumentException(
          "Invalid File Format! File contains extra or missing columns");
    }
    Arrays.stream(Constants.CSV_HEADERS)
        .forEach(
            headerName -> {
              if (!headers.containsKey(headerName)) {
                throw new IllegalArgumentException(
                    "Invalid File Format! File is missing " + headerName + " column!");
              }
            });
  }
}
