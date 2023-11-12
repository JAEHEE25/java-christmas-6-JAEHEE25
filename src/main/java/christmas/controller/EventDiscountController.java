package christmas.controller;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.contants.EventPeriod;
import christmas.util.Calculator;

public class EventDiscountController {
    public int calculateChristmasDiscount(VisitDate visitDate) {
        int totalDiscount = 0;

        if (isChristmasEventPeriod(visitDate)) {
            totalDiscount = EventPeriod.CHRISTMAS_EVENT.getStartMoney();
            int discountDays = visitDate.getChristmasEventDiscountDays();
            totalDiscount += Calculator.multiplication(discountDays, EventPeriod.CHRISTMAS_EVENT.getDiscountAmount());
        }
        return totalDiscount;
    }

    private boolean isChristmasEventPeriod(VisitDate visitDate) {
        return visitDate.isChristmasEventPeriod();
    }

    public int calculateWeekdayDiscount(VisitDate visitDate, Order order) {
        return 0;
    }
}
