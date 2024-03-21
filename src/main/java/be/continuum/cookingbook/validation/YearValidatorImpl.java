package be.continuum.cookingbook.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearValidatorImpl implements ConstraintValidator<YearValidator, Year> {
    private Year minimum;

    @Override
    public void initialize(YearValidator yearValidator) {
        minimum = Year.of(yearValidator.minimum());
    }

    @Override
    public boolean isValid(Year value, ConstraintValidatorContext cxt) {
        return  (minimum.isBefore(value) || minimum.equals(value)) && (Year.now().isAfter(value) || Year.now().equals(value));
    }
}
