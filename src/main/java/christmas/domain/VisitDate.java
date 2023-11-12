package christmas.domain;

import christmas.domain.contants.EventPeriod;
import christmas.util.Calculator;
import christmas.util.Parser;
import christmas.validator.DateNumberFormatValidator;
import christmas.validator.InRangeNumberValidator;

public class VisitDate {
    private final int visitDate;

    public VisitDate(String playerInput) {
        validateNumberFormat(playerInput);
        int inputDate = Parser.toInt(playerInput);
        validateNumberRange(inputDate);
        visitDate = inputDate;
    }

    private void validateNumberFormat(String playerInput) {
        DateNumberFormatValidator dateNumberFormatValidator = new DateNumberFormatValidator();
        dateNumberFormatValidator.validate(playerInput);
    }

    private void validateNumberRange(int inputDate) {
        InRangeNumberValidator inRangeNumberValidator = new InRangeNumberValidator();
        inRangeNumberValidator.validate(inputDate);
    }

    public boolean isChristmasEventPeriod() {
        return visitDate >= EventPeriod.CHRISTMAS_EVENT.getStartDay() ||
                visitDate <= EventPeriod.CHRISTMAS_EVENT.getEndDay();
    }

    public int getChristmasEventDiscountDays() {
        return Calculator.minus(visitDate, EventPeriod.CHRISTMAS_EVENT.getStartDay());
    }
}
