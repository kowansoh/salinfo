package com.dave.salinfo.ExceptionHandler;

import com.dave.salinfo.bean.SalaryResponseBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
  protected ResponseEntity<SalaryResponseBean> handleConflict(RuntimeException ex) {
    return ResponseEntity.badRequest().body(new SalaryResponseBean(0, ex.getMessage()));
  }
}
