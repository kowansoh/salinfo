package com.dave.salinfo.controller;

import com.dave.salinfo.Constants;
import com.dave.salinfo.annotation.AllowedValues;
import com.dave.salinfo.bean.SalaryListBean;
import com.dave.salinfo.bean.SalaryResponseBean;
import com.dave.salinfo.factory.FileProcessingFactory;
import com.dave.salinfo.service.SalaryInfoService;
import com.sun.jdi.InvalidTypeException;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Validated
public class SalaryController {
  @Autowired private SalaryInfoService salaryInfoService;
  @Autowired private FileProcessingFactory fileProcessingFactory;

  @GetMapping("/users")
  public ResponseEntity<SalaryListBean> getSalary(
      @RequestParam(defaultValue = "0.0") @DecimalMin("0.00") BigDecimal min,
      @RequestParam(defaultValue = "4000.0") @DecimalMin("0.00") BigDecimal max,
      @RequestParam(defaultValue = "0") @PositiveOrZero int offset,
      @RequestParam(required = false) @PositiveOrZero Integer limit,
      @RequestParam(required = false)
          @AllowedValues(
              values = {"NAME", "SALARY"},
              message = "Only name and salary are allowed for sorting")
          String sort) {
    SalaryListBean response =
        salaryInfoService.getSalaryInfo(min.floatValue(), max.floatValue(), offset, limit, sort);

    return ResponseEntity.ok().body(response);
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
