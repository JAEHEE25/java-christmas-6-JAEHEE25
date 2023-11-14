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

    private String getChristmasEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = calculateChristmasDiscount(visitDate, order);
        String eventHistory = getEventHistoryForm(ReportPhrase.CHRISTMAS_DISCOUNT.getPhrase(), totalDiscount);
        return getBlankOrHistoryByTotalDiscount(totalDiscount, eventHistory);
    }

    private String getWeekdayEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = calculateWeekdayDiscount(visitDate, order);
        String eventHistory = getEventHistoryForm(ReportPhrase.WEEKDAY_DISCOUNT.getPhrase(), totalDiscount);
        return getBlankOrHistoryByTotalDiscount(totalDiscount, eventHistory);
    }

    private String getWeekendEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = calculateWeekendDiscount(visitDate, order);
        String eventHistory = getEventHistoryForm(ReportPhrase.WEEKEND_DISCOUNT.getPhrase(), totalDiscount);
        return getBlankOrHistoryByTotalDiscount(totalDiscount, eventHistory);
    }

    private String getSpecialEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = calculateSpecialDiscount(visitDate, order);
        String eventHistory = getEventHistoryForm(ReportPhrase.SPECIAL_DISCOUNT.getPhrase(), totalDiscount);
        return getBlankOrHistoryByTotalDiscount(totalDiscount, eventHistory);
    }

    private String getPresentEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = calculatePresentDiscount(visitDate, order);
        String eventHistory = getEventHistoryForm(ReportPhrase.PRESENT_EVENT.getPhrase(), totalDiscount);
        return getBlankOrHistoryByTotalDiscount(totalDiscount, eventHistory);
    }

    private String getEventHistoryForm(String title, int totalDiscount) {
        return title + ReportPhrase.COLON.getPhrase() +
                ReportPhrase.MINUS.getPhrase() + Parser.toThousandUnitMoney(totalDiscount) + "\n";
    }

    private String getBlankOrHistoryByTotalDiscount(int totalDiscount, String eventHistory) {
        if (totalDiscount == 0) {
            return "";
        }
        return eventHistory;
    }

    public String getEventHistory(VisitDate visitDate, Order order) {
        StringBuilder eventHistory = new StringBuilder();
        eventHistory.append(getChristmasEventHistory(visitDate, order));
        eventHistory.append(getWeekdayEventHistory(visitDate, order));
        eventHistory.append(getWeekendEventHistory(visitDate, order));
        eventHistory.append(getSpecialEventHistory(visitDate, order));
        eventHistory.append(getPresentEventHistory(visitDate, order));

        return eventHistory.toString();
    }
}
