package com.dave.salinfo.validator;

import com.dave.salinfo.annotation.AllowedValues;
import java.util.Arrays;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AllowedValueValidator implements ConstraintValidator<AllowedValues, String> {
  private String[] allowedValues;
  private String message;

  @Override
  public void initialize(AllowedValues allowedValues) {
    this.allowedValues = allowedValues.values();
    this.message = allowedValues.message();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    if (!(Objects.isNull(value) || value.isEmpty())
        && Arrays.stream(allowedValues)
            .noneMatch(allowedValue -> allowedValue.equalsIgnoreCase(value))) {

      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(message).addBeanNode().addConstraintViolation();
      return false;
    }
    return true;
  }
}
