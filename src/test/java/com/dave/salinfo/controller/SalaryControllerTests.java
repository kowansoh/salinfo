package com.dave.salinfo.controller;

import com.dave.salinfo.bean.SalaryListBean;
import com.dave.salinfo.bean.SalaryResponseBean;
import com.dave.salinfo.factory.FileProcessingFactory;
import com.dave.salinfo.processor.SalaryFileProcessor;
import com.dave.salinfo.service.SalaryInfoService;
import com.sun.jdi.InvalidTypeException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
class SalaryControllerTests {

  @InjectMocks SalaryController classUnderTest;
  @Mock FileProcessingFactory fileProcessingFactory;
  @Mock SalaryInfoService salaryInfoService;

  @Test
  @DisplayName("Get Salary Info without limit and sorting")
  void testGetSalary() {
    SalaryListBean salaryList = new SalaryListBean();
    Mockito.when(
            salaryInfoService.getSalaryInfo(
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyInt(),
                Mockito.any(),
                Mockito.any()))
        .thenReturn(salaryList);
    ResponseEntity<SalaryListBean> responseEntity =
        classUnderTest.getSalary(new BigDecimal(1), new BigDecimal(1), 0, null, null);
    Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  @DisplayName("Upload Salary with CSV file type")
  void testUploadSalary() throws InvalidTypeException {
    MultipartFile file = Mockito.mock(MultipartFile.class);
    SalaryFileProcessor processor = Mockito.mock(SalaryFileProcessor.class);
    Mockito.when(file.getContentType()).thenReturn("csv");
    Mockito.when(fileProcessingFactory.getProcessor(Mockito.anyString())).thenReturn(processor);
    ResponseEntity<SalaryResponseBean> responseEntity = classUnderTest.uploadSalary(file);
    Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  @DisplayName("Upload Salary with invalid file type")
  void testUploadSalaryInvalidFile() throws InvalidTypeException {
    MultipartFile file = Mockito.mock(MultipartFile.class);
    Mockito.when(file.getContentType()).thenReturn("txt");
    Mockito.when(fileProcessingFactory.getProcessor(Mockito.anyString()))
        .thenThrow(new InvalidTypeException("Invalid File"));

    Assertions.assertThrows(InvalidTypeException.class, () -> classUnderTest.uploadSalary(file));
  }

  @Test
  @DisplayName("Upload Salary no file")
  void testUploadSalaryNoFile() {
    MultipartFile file = Mockito.mock(MultipartFile.class);

    Assertions.assertThrows(InvalidTypeException.class, () -> classUnderTest.uploadSalary(file));
  }
}
