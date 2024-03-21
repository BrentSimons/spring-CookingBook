package be.continuum.cookingbook.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YearValidatorImpl.class)
public @interface YearValidator {
    String message() default "must be between {minimum} and current year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int minimum();
}
