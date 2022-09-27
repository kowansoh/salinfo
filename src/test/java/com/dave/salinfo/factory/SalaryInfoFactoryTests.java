package com.dave.salinfo.factory;

import com.dave.salinfo.processor.SalaryCSVProcessor;
import com.dave.salinfo.processor.SalaryFileProcessor;
import com.sun.jdi.InvalidTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SalaryInfoFactoryTests {

  @InjectMocks FileProcessingFactory classUnderTest;
  @Mock SalaryCSVProcessor csvSalaryProcessor;

  @Test
  @DisplayName("Get Processor for CSV File Type")
  void testGetProcessor() throws InvalidTypeException {
    SalaryFileProcessor response = classUnderTest.getProcessor("csv");

    Assertions.assertEquals(csvSalaryProcessor.getClass(), response.getClass());
  }

  @Test
  @DisplayName("Get Processor for invalid File Type")
  void testGetProcessorInvalidType() {
    Assertions.assertThrows(InvalidTypeException.class, () -> classUnderTest.getProcessor("test"));
  }
}
