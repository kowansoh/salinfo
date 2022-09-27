package com.dave.salinfo.validator;

import com.dave.salinfo.annotation.AllowedValues;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.LeafNodeBuilderCustomizableContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AllowedValueValidatorTests {

  @InjectMocks AllowedValueValidator classUnderTest;

  @Test
  @DisplayName("Validate valid string value")
  void testIsValid() {
    AllowedValues allowedValues = Mockito.mock(AllowedValues.class);
    ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class);

    String[] allowedList = new String[] {"TEST", "TEST2"};
    Mockito.when(allowedValues.values()).thenReturn(allowedList);
    classUnderTest.initialize(allowedValues);
    Assertions.assertTrue(classUnderTest.isValid("TEST", context));
  }

  @Test
  @DisplayName("Validate null string value")
  void testIsValidNullString() {
    AllowedValues allowedValues = Mockito.mock(AllowedValues.class);
    ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class);

    String[] allowedList = new String[] {"TEST", "TEST2"};
    Mockito.when(allowedValues.values()).thenReturn(allowedList);
    Assertions.assertTrue(classUnderTest.isValid(null, context));
  }

  @Test
  @DisplayName("Validate empty string value")
  void testIsValidEmptyString() {
    AllowedValues allowedValues = Mockito.mock(AllowedValues.class);
    ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class);

    String[] allowedList = new String[] {"TEST", "TEST2"};
    Mockito.when(allowedValues.values()).thenReturn(allowedList);
    Assertions.assertTrue(classUnderTest.isValid("", context));
  }

  @Test
  @DisplayName("Validate invalid string value")
  void testIsValidInvalidString() {
    AllowedValues allowedValues = Mockito.mock(AllowedValues.class);
    ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class);
    ConstraintViolationBuilder contextBuilder = Mockito.mock(ConstraintViolationBuilder.class);
    LeafNodeBuilderCustomizableContext leafNodeContext =
        Mockito.mock(LeafNodeBuilderCustomizableContext.class);
    String[] allowedList = new String[] {"TEST", "TEST2"};
    Mockito.when(allowedValues.values()).thenReturn(allowedList);
    Mockito.when(allowedValues.message()).thenReturn("INVALID");

    Mockito.when(context.buildConstraintViolationWithTemplate(Mockito.anyString()))
        .thenReturn(contextBuilder);
    Mockito.when(contextBuilder.addBeanNode()).thenReturn(leafNodeContext);
    Mockito.when(leafNodeContext.addConstraintViolation()).thenReturn(context);
    classUnderTest.initialize(allowedValues);
    Assertions.assertFalse(classUnderTest.isValid("TEST3", context));
  }
}
