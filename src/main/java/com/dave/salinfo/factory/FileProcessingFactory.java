package com.dave.salinfo.factory;

import com.dave.salinfo.Constants;
import com.dave.salinfo.processor.SalaryCSVProcessor;
import com.dave.salinfo.processor.SalaryFileProcessor;
import com.sun.jdi.InvalidTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class FileProcessingFactory {

  @Lazy @Autowired private SalaryCSVProcessor csvSalaryProcessor;

  public SalaryFileProcessor getProcessor(String type) throws InvalidTypeException {
    if (type.toLowerCase(Locale.ROOT).contains("csv")) {
      return getCsvFileProcessor();
    }
    throw new InvalidTypeException(Constants.INVALID_FILE_TYPE);
  }

  private SalaryFileProcessor getCsvFileProcessor() {
    return csvSalaryProcessor;
  }
}
