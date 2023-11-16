package christmas.controller;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.contants.benefitReport.ReportPhrase;
import christmas.domain.contants.eventInfo.*;
import christmas.domain.contants.order.MenuBoard;
import christmas.util.Calculator;
import christmas.util.Parser;

public class EventDiscountController {
    public int calculateChristmasDiscount(VisitDate visitDate, Order order) {
        int totalDiscount = 0;

        if (visitDate.isChristmasEventPeriod() && isMeetChristmasEventCriteria(order)) {
            totalDiscount = ChristmasEventAmount.EVENT_AMOUNT.getStartMoney();
            int discountDays = visitDate.getChristmasEventDiscountDays();
            totalDiscount += Calculator.multiplication(discountDays, ChristmasEventAmount.EVENT_AMOUNT.getDiscountAmount());
        }
        return totalDiscount;
    }

    public int calculateWeekdayDiscount(VisitDate visitDate, Order order) {
        int totalDiscount = 0;

        if (visitDate.isWeekDayEventPeriod() && isMeetDefaultEventCriteria(order)) {
            int weekdayEventMenuCount = order.getWeekEventMenuCount(visitDate);
            totalDiscount = WeekEventInfo.getDiscountAmountByDayWithCount(visitDate, weekdayEventMenuCount);
        }
        return totalDiscount;
    }

    public int calculateWeekendDiscount(VisitDate visitDate, Order order) {
        int totalDiscount = 0;

        if (visitDate.isWeekendEventPeriod() && isMeetDefaultEventCriteria(order)) {
            int weekendEventMenuCount = order.getWeekEventMenuCount(visitDate);
            totalDiscount = WeekEventInfo.getDiscountAmountByDayWithCount(visitDate, weekendEventMenuCount);
        }
        return totalDiscount;
    }

    public int calculateSpecialDiscount(VisitDate visitDate, Order order) {
        int totalDiscount = 0;

        if (visitDate.isSpecialEventDate() && isMeetDefaultEventCriteria(order)) {
            totalDiscount += SpecialEventDate.getDiscountAmountByDate(visitDate);
        }
        return totalDiscount;
    }

    public int calculatePresentDiscount(VisitDate visitDate, Order order) {
        int totalDiscount = 0;

        if (isMeetPresentEventCriteria(visitDate, order)) {
            String present = PresentEventInfo.CHAMPAGNE_EVENT.getPresent();
            int presentPrice = MenuBoard.getPriceByMenu(present);
            int presentCount = PresentEventInfo.CHAMPAGNE_EVENT.getCount();

            totalDiscount += Calculator.multiplication(presentPrice, presentCount);
        }
        return totalDiscount;
    }

    public int calculateTotalBenefitAmount(VisitDate visitDate, Order order) {
        return Calculator.addAllValues(
                calculateChristmasDiscount(visitDate, order), calculateWeekdayDiscount(visitDate, order),
                calculateWeekendDiscount(visitDate, order), calculateSpecialDiscount(visitDate, order),
                calculatePresentDiscount(visitDate, order)
        );
    }

    private boolean isMeetChristmasEventCriteria(Order order) {
        return order.isMeetCriteria(EventPeriod.CHRISTMAS_EVENT.getMoneyCriteria());
    }

    private boolean isMeetDefaultEventCriteria(Order order) {
        return order.isMeetCriteria(EventPeriod.DEFAULT_EVENT.getMoneyCriteria());
    }

    private boolean isMeetPresentEventCriteria(VisitDate visitDate, Order order) {
        return visitDate.isDefaultEventPeriod() && isMeetDefaultEventCriteria(order) &&
                order.isMeetCriteria(PresentEventInfo.CHAMPAGNE_EVENT.getMoneyCriteria());
    }

}
