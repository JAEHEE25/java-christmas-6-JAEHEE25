package christmas.domain;

import christmas.domain.contants.EventDate;
import christmas.domain.contants.EventPeriod;
import christmas.domain.contants.WeekdayEventInfo;
import christmas.util.Calculator;
import christmas.util.Parser;
import christmas.validator.DateNumberFormatValidator;
import christmas.validator.InRangeNumberValidator;

import java.time.LocalDate;

public class VisitDate {
    private final int visitDate;
    private final int visitDay;

    public VisitDate(String playerInput) {
        validateNumberFormat(playerInput);
        int inputDate = Parser.toInt(playerInput);
        validateNumberRange(inputDate);
        visitDate = inputDate;

        LocalDate todayDate = LocalDate.of(EventDate.EVENT_YEAR.getNumber(),
                EventDate.EVENT_MONTH.getNumber(), visitDate);
        visitDay = todayDate.getDayOfWeek().getValue();
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
        return visitDate >= EventPeriod.CHRISTMAS_EVENT.getStartDay() &&
                visitDate <= EventPeriod.CHRISTMAS_EVENT.getEndDay();
    }

    public int getChristmasEventDiscountDays() {
        return Calculator.minus(visitDate, EventPeriod.CHRISTMAS_EVENT.getStartDay());
    }

    public boolean isWeekDayEventPeriod() {
        boolean isEventPeriod = visitDate >= EventPeriod.DEFAULT_EVENT.getStartDay() &&
                visitDate <= EventPeriod.DEFAULT_EVENT.getEndDay();
        return isEventPeriod && WeekdayEventInfo.isWeekday(visitDay);
    }

    public boolean isSameDay(int day) {
        return visitDay == day;
    }

}

