package com.dave.salinfo.exceptionhandler;

import com.dave.salinfo.bean.SalaryResponseBean;
import com.sun.jdi.InvalidTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(
      value = {
        IllegalArgumentException.class,
        IllegalStateException.class,
        InvalidTypeException.class,
        NumberFormatException.class
      })
  protected ResponseEntity<SalaryResponseBean> handleBadRequest(Exception ex) {
    return ResponseEntity.badRequest().body(new SalaryResponseBean(0, ex.getMessage()));
  }

  @ExceptionHandler(value = {NullPointerException.class})
  protected ResponseEntity<SalaryResponseBean> handleNull(Exception ex) {
    return ResponseEntity.badRequest().body(new SalaryResponseBean(0, ex.getMessage()));
  }

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<SalaryResponseBean> handleRestOfExceptions(Exception ex) {
    return ResponseEntity.internalServerError().body(new SalaryResponseBean(0, ex.getMessage()));
  }
}
