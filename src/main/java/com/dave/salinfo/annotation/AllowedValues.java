package com.dave.salinfo.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import com.dave.salinfo.validator.AllowedValueValidator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowedValueValidator.class)
public @interface AllowedValues {
  String[] values() default {};

  public String message() default "Value is not allowed";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};
}
