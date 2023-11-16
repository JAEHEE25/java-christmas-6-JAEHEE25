package christmas.domain;

import christmas.domain.contants.eventInfo.EventDate;
import christmas.domain.contants.eventInfo.EventPeriod;
import christmas.domain.contants.eventInfo.SpecialEventDate;
import christmas.domain.contants.eventInfo.WeekEventInfo;
import christmas.util.Calculator;
import christmas.util.Parser;
import christmas.validator.VisitDateNumberFormatValidator;
import christmas.validator.VisitDateInRangeNumberValidator;

import java.time.LocalDate;

public class VisitDate {
    private final int visitDate;

    public VisitDate(String playerInput) {
        validateNumberFormat(playerInput);
        int inputDate = Parser.toInt(playerInput);
        validateNumberRange(inputDate);
        visitDate = inputDate;
    }

    private void validateNumberFormat(String playerInput) {
        VisitDateNumberFormatValidator visitDateNumberFormatValidator = new VisitDateNumberFormatValidator();
        visitDateNumberFormatValidator.validate(playerInput);
    }

    private void validateNumberRange(int inputDate) {
        VisitDateInRangeNumberValidator visitDateInRangeNumberValidator = new VisitDateInRangeNumberValidator();
        visitDateInRangeNumberValidator.validate(inputDate);
    }

    public int getVisitDate() {
        return visitDate;
    }

    private int getVisitDay() {
        LocalDate todayDate = LocalDate.of(EventDate.EVENT_YEAR.getNumber(),
                EventDate.EVENT_MONTH.getNumber(), visitDate);
        return todayDate.getDayOfWeek().getValue();
    }

    public int getChristmasEventDiscountDays() {
        return Calculator.minus(visitDate, EventPeriod.CHRISTMAS_EVENT.getStartDay());
    }

    public boolean isChristmasEventPeriod() {
        return visitDate >= EventPeriod.CHRISTMAS_EVENT.getStartDay() &&
                visitDate <= EventPeriod.CHRISTMAS_EVENT.getEndDay();
    }

    public boolean isDefaultEventPeriod() {
        return visitDate >= EventPeriod.DEFAULT_EVENT.getStartDay() &&
                visitDate <= EventPeriod.DEFAULT_EVENT.getEndDay();
    }

    public boolean isWeekDayEventPeriod() {
        return isDefaultEventPeriod() && WeekEventInfo.isWeekday(getVisitDay());
    }

    public boolean isWeekendEventPeriod() {
        return isDefaultEventPeriod() && WeekEventInfo.isWeekend(getVisitDay());
    }

    public boolean isSpecialEventDate() {
        return isDefaultEventPeriod() && SpecialEventDate.isSpecialEventDate(visitDate);
    }

    public boolean isSameDate(int date) {
        return visitDate == date;
    }

    public boolean isSameDay(int day) {
        return getVisitDay() == day;
    }
}

