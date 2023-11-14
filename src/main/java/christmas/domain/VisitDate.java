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
        VisitDateNumberFormatValidator visitDateNumberFormatValidator = new VisitDateNumberFormatValidator();
        visitDateNumberFormatValidator.validate(playerInput);
    }

    private void validateNumberRange(int inputDate) {
        VisitDateInRangeNumberValidator visitDateInRangeNumberValidator = new VisitDateInRangeNumberValidator();
        visitDateInRangeNumberValidator.validate(inputDate);
    }

    public boolean isChristmasEventPeriod() {
        return visitDate >= EventPeriod.CHRISTMAS_EVENT.getStartDay() &&
                visitDate <= EventPeriod.CHRISTMAS_EVENT.getEndDay();
    }

    public int getChristmasEventDiscountDays() {
        return Calculator.minus(visitDate, EventPeriod.CHRISTMAS_EVENT.getStartDay());
    }

    public boolean isWeekDayEventPeriod() {
        return isDefaultEventPeriod() && WeekEventInfo.isWeekday(visitDay);
    }

    public boolean isWeekendEventPeriod() {
        return isDefaultEventPeriod() && WeekEventInfo.isWeekend(visitDay);
    }

    public boolean isDefaultEventPeriod() {
        return visitDate >= EventPeriod.DEFAULT_EVENT.getStartDay() &&
                visitDate <= EventPeriod.DEFAULT_EVENT.getEndDay();
    }

    public boolean isSpecialEventDate() {
        return isDefaultEventPeriod() && SpecialEventDate.isSpecialEventDate(visitDate);
    }

    public boolean isSameDate(int date) {
        return visitDate == date;
    }

    public boolean isSameDay(int day) {
        return visitDay == day;
    }

    public int getVisitDate() {
        return visitDate;
    }
}

