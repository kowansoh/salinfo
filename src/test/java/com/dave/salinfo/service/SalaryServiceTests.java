package com.dave.salinfo.service;

import com.dave.salinfo.bean.SalaryInfoBean;
import com.dave.salinfo.bean.SalaryListBean;
import com.dave.salinfo.repository.SalaryInfoRepositoryImpl;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SalaryServiceTests {

  @InjectMocks SalaryInfoService classUnderTest;
  @Mock SalaryInfoRepositoryImpl salaryRepository;

  @Test
  @DisplayName("Get Salary Info")
  void testGetSalaryInfo() {
    List<SalaryInfoBean> salaryList = List.of(new SalaryInfoBean("name", 1));

    Mockito.when(
            salaryRepository.getSalaryInfoBySalaryBetween(
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyInt(),
                Mockito.any(),
                Mockito.any()))
        .thenReturn(salaryList);
    SalaryListBean response = classUnderTest.getSalaryInfo(0f, 1f, 0, null, null);
    Assertions.assertEquals(salaryList, response.getResults());
  }
}
