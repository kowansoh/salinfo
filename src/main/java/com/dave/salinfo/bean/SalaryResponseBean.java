package com.dave.salinfo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryResponseBean {
    private int success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
}
