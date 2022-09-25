package com.dave.salinfo.processor;

import com.dave.salinfo.Constants;
import com.dave.salinfo.entity.SalaryInfo;
import com.dave.salinfo.repository.SalaryInfoRepository;
import com.dave.salinfo.utils.CsvUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
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
      Iterable<CSVRecord> csvRecords =
          CsvUtils.readCsv(file.getInputStream(), Constants.CSV_HEADERS);

      List<SalaryInfo> salaryInfos = new ArrayList<>();

      for (CSVRecord csvRecord : csvRecords) {
        float salary = Float.parseFloat(csvRecord.get("SALARY"));
        if (salary >= 0f) {
          SalaryInfo salaryInfo = new SalaryInfo(csvRecord.get("NAME").trim(), salary);
          salaryInfos.add(salaryInfo);
        }
      }

      salaryRepository.saveAll(salaryInfos);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }
}
