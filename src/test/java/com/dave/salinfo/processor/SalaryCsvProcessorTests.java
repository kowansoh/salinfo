package com.dave.salinfo.processor;

import com.dave.salinfo.repository.SalaryInfoRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
class SalaryCsvProcessorTests {

  @InjectMocks SalaryCSVProcessor classUnderTest;
  @Mock SalaryInfoRepository salaryRepository;

  @Test
  @DisplayName("Process valid CSV file")
  void testProcessSalaryFile() throws IOException {
    String name = "test.csv";
    String originalFileName = "test.csv";
    String contentType = "text/csv";
    File testFile = new File("src/test/resources/test.csv");
    MultipartFile file =
        new MockMultipartFile(name, originalFileName, contentType, new FileInputStream(testFile));
    classUnderTest.processSalaryFile(file);
    Mockito.verify(salaryRepository, Mockito.times(1)).saveAll(Mockito.any());
  }

  @Test
  @DisplayName("Process invalid CSV file with wrong header name")
  void testProcessSalaryFileInvalidStructure() throws IOException {
    String name = "test.csv";
    String originalFileName = "test.csv";
    String contentType = "text/csv";
    File testFile = new File("src/test/resources/test_invalid.csv");
    MultipartFile file =
        new MockMultipartFile(name, originalFileName, contentType, new FileInputStream(testFile));
    Mockito.verify(salaryRepository, Mockito.times(0)).saveAll(Mockito.any());
    Assert.assertThrows(
        IllegalArgumentException.class, () -> classUnderTest.processSalaryFile(file));
  }

  @Test
  @DisplayName("Process invalid CSV file with wrong number of column")
  void testProcessSalaryFileIncorrectColumns() throws IOException {
    String name = "test.csv";
    String originalFileName = "test.csv";
    String contentType = "text/csv";
    File testFile = new File("src/test/resources/test_invalid_column.csv");
    MultipartFile file =
        new MockMultipartFile(name, originalFileName, contentType, new FileInputStream(testFile));
    Mockito.verify(salaryRepository, Mockito.times(0)).saveAll(Mockito.any());
    Assert.assertThrows(
        IllegalArgumentException.class, () -> classUnderTest.processSalaryFile(file));
  }
}
