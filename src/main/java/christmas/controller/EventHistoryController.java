package christmas.controller;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.contants.benefitReport.ReportPhrase;
import christmas.util.Parser;

public class EventHistoryController {
    private final EventDiscountController eventDiscountController = new EventDiscountController();

    private String getChristmasEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = eventDiscountController.calculateChristmasDiscount(visitDate, order);
        String eventHistory = getEventHistoryForm(ReportPhrase.CHRISTMAS_DISCOUNT.getPhrase(), totalDiscount);
        return getBlankOrHistoryByTotalDiscount(totalDiscount, eventHistory);
    }

    private String getWeekdayEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = eventDiscountController.calculateWeekdayDiscount(visitDate, order);
        String eventHistory = getEventHistoryForm(ReportPhrase.WEEKDAY_DISCOUNT.getPhrase(), totalDiscount);
        return getBlankOrHistoryByTotalDiscount(totalDiscount, eventHistory);
    }

    private String getWeekendEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = eventDiscountController.calculateWeekendDiscount(visitDate, order);
        String eventHistory = getEventHistoryForm(ReportPhrase.WEEKEND_DISCOUNT.getPhrase(), totalDiscount);
        return getBlankOrHistoryByTotalDiscount(totalDiscount, eventHistory);
    }

    private String getSpecialEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = eventDiscountController.calculateSpecialDiscount(visitDate, order);
        String eventHistory = getEventHistoryForm(ReportPhrase.SPECIAL_DISCOUNT.getPhrase(), totalDiscount);
        return getBlankOrHistoryByTotalDiscount(totalDiscount, eventHistory);
    }

    private String getPresentEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = eventDiscountController.calculatePresentDiscount(visitDate, order);
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
