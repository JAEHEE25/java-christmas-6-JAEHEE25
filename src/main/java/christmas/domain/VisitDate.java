package christmas.domain;

import christmas.validator.NumberFormatValidator;
import christmas.validator.OutOfRangeNumberValidator;
import christmas.validator.Validator;

public class VisitDate {
    private final int visitDate;

    public VisitDate(String playerInput) {
        validateNumberFormat(playerInput);
        int inputDate = toInt(playerInput);
        validateNumberRange(inputDate);
        visitDate = inputDate;
    }

    private int toInt(String playerInput) {
        return Integer.parseInt(playerInput);
    }

    private void validateNumberFormat(String playerInput) {
        NumberFormatValidator numberFormatValidator = new NumberFormatValidator();
        numberFormatValidator.validate(playerInput);
    }

    private void validateNumberRange(int inputDate) {
        OutOfRangeNumberValidator outOfRangeNumberValidator = new OutOfRangeNumberValidator();
        outOfRangeNumberValidator.validate(inputDate);
    }
}
