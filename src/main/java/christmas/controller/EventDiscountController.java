package christmas.controller;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.contants.*;
import christmas.util.Calculator;

public class EventDiscountController {
    public int calculateChristmasDiscount(VisitDate visitDate) {
        int totalDiscount = 0;

        if (visitDate.isChristmasEventPeriod()) {
            totalDiscount = ChristmasEventInfo.EVENT_MONEY.getStartMoney();
            int discountDays = visitDate.getChristmasEventDiscountDays();
            totalDiscount += Calculator.multiplication(discountDays, ChristmasEventInfo.EVENT_MONEY.getDiscountAmount());
        }
        return totalDiscount;
    }

    public int calculateWeekdayDiscount(VisitDate visitDate, Order order) {
        int totalDiscount = 0;

        if (visitDate.isWeekDayEventPeriod()) {
            int weekdayEventMenuCount = order.getWeekEventMenuCount(visitDate);
            totalDiscount = WeekEventInfo.getDiscountAmountByDayWithCount(visitDate, weekdayEventMenuCount);
        }
        return totalDiscount;
    }

    public int calculateWeekendDiscount(VisitDate visitDate, Order order) {
        int totalDiscount = 0;

        if (visitDate.isWeekendEventPeriod()) {
            int weekendEventMenuCount = order.getWeekEventMenuCount(visitDate);
            totalDiscount = WeekEventInfo.getDiscountAmountByDayWithCount(visitDate, weekendEventMenuCount);
        }
        return totalDiscount;
    }

    public int calculateSpecialDiscount(VisitDate visitDate) {
        int totalDiscount = 0;

        if (visitDate.isSpecialEventDate()) {
            totalDiscount += SpecialEventDate.getDiscountAmountByDate(visitDate);
        }
        return totalDiscount;
    }

    public int calculatePresentDiscount(VisitDate visitDate, Order order) {
        int totalDiscount = 0;

        if (visitDate.isDefaultEventPeriod() &&
                order.calculateToTalOrderAmount() >= PresentEventInfo.CHAMPAGNE_EVENT.getMoneyCriteria()) {
            String present = PresentEventInfo.CHAMPAGNE_EVENT.getPresent();
            int presentPrice = MenuBoard.getPriceByMenu(present);
            int presentCount = PresentEventInfo.CHAMPAGNE_EVENT.getCount();

            totalDiscount += Calculator.multiplication(presentPrice, presentCount);
        }
        return totalDiscount;
    }
}
