package com.dave.salinfo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalaryResponseBean {

  private int success;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String error;

  public SalaryResponseBean() {
    this.success = 1;
  }
}
