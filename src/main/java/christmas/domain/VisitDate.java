package christmas.domain;

import christmas.util.Parser;
import christmas.validator.NumberFormatValidator;
import christmas.validator.OutOfRangeNumberValidator;

public class VisitDate {
    private final int visitDate;

    public VisitDate(String playerInput) {
        validateNumberFormat(playerInput);
        int inputDate = Parser.toInt(playerInput);
        validateNumberRange(inputDate);
        visitDate = inputDate;
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
