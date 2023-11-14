package christmas.domain;

import christmas.domain.contants.benefitReport.BenefitReportTitle;
import christmas.domain.contants.benefitReport.ReportPhrase;
import christmas.domain.contants.eventInfo.EventBadge;
import christmas.domain.contants.eventInfo.PresentEventInfo;
import christmas.util.Parser;

import java.util.Map;

public class BenefitReport {
    private final Map<String, String> benefitReport;

    public BenefitReport() {
        this.benefitReport = BenefitReportTitle.createBenefitReport();
    }

    private String getOrderMenuList(Order order) {
        Map<OrderMenu, OrderCount> orderResult = order.getOrder();
        StringBuilder orderMenuList = new StringBuilder();

        for (OrderMenu orderMenu : orderResult.keySet()) {
            int orderCount = orderResult.get(orderMenu).getOrderCount();
            orderMenuList.append(orderMenu.getOrderMenu()).append(" ").
                    append(orderCount).append(ReportPhrase.MENU_UNIT.getPhrase()).append("\n");
        }
        return orderMenuList.toString();
    }

    public void putOrderMenuList(Order order) {
        benefitReport.put(BenefitReportTitle.ORDER_MENU_LIST.getTitle(), getOrderMenuList(order));
    }

    public void putTotalOrderAmount(Order order) {
        benefitReport.put(BenefitReportTitle.TOTAL_ORDER_AMOUNT.getTitle(),
                Parser.toThousandUnitMoney(order.getTotalOrderAmount()) + "\n");
    }

    private String getPresentMenu(int totalDiscount) {
        if (totalDiscount == 0) {
            return getNothingPhrase();
        }
        return PresentEventInfo.CHAMPAGNE_EVENT.getPresent() + " " +
                PresentEventInfo.CHAMPAGNE_EVENT.getCount() + ReportPhrase.MENU_UNIT.getPhrase() + "\n";
    }

    public void putPresentMenu(int totalDiscount) {
        benefitReport.put(BenefitReportTitle.PRESENT_MENU.getTitle(), getPresentMenu(totalDiscount));
    }

    private String getEventHistory(String eventHistory) {
        if (eventHistory.isBlank()) {
            return getNothingPhrase();
        }
        return eventHistory;
    }

    public void putEventHistory(String eventHistory) {
        benefitReport.put(BenefitReportTitle.EVENT_HISTORY.getTitle(), getEventHistory(eventHistory));
    }

    private String getTotalBenefitAmount(int totalBenefitAmount) {
        String totalBenefitAmountResult = Parser.toThousandUnitMoney(totalBenefitAmount) + "\n";
        if (totalBenefitAmount == 0) {
            return totalBenefitAmountResult;
        }
        return ReportPhrase.MINUS.getPhrase() + totalBenefitAmountResult;
    }

    public void putTotalBenefitAmount(int totalBenefitAmount) {
        benefitReport.put(BenefitReportTitle.TOTAL_BENEFIT_AMOUNT.getTitle(), getTotalBenefitAmount(totalBenefitAmount));
    }

    public void putPaymentAmount(int totalPaymentAmount) {
        benefitReport.put(BenefitReportTitle.PAYMENT_AMOUNT.getTitle(),
                Parser.toThousandUnitMoney(totalPaymentAmount) + "\n");
    }

    private String getEventBadge(int totalBenefitAmount) {
        String eventBadge = EventBadge.getBadgeNameByBenefitAmount(totalBenefitAmount);
        if (eventBadge.isBlank()) {
            return getNothingPhrase();
        }
        return eventBadge;
    }

    public void putEventBadge(int totalBenefitAmount) {
        benefitReport.put(BenefitReportTitle.EVENT_BADGE.getTitle(), getEventBadge(totalBenefitAmount));
    }

    private String getNothingPhrase() {
        return ReportPhrase.NOTHING.getPhrase() + "\n";
    }

    public Map<String, String> getBenefitReport() {
        return benefitReport;
    }
}
