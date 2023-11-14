package christmas.domain;

import christmas.domain.contants.benefitReport.BenefitReportInfo;
import christmas.domain.contants.benefitReport.ReportSetting;
import christmas.domain.contants.eventInfo.EventBadge;
import christmas.domain.contants.eventInfo.PresentEventInfo;
import christmas.util.Parser;

import java.util.Map;

public class BenefitReport {
    private final Map<String, String> benefitReport;

    public BenefitReport() {
        this.benefitReport = BenefitReportInfo.createBenefitReport();
    }

    private String getOrderMenuList(Order order) {
        Map<OrderedMenu, OrderedCount> orderResult = order.getOrder();
        StringBuilder orderMenuList = new StringBuilder();

        for (OrderedMenu orderedMenu : orderResult.keySet()) {
            int orderCount = orderResult.get(orderedMenu).getOrderedCount();
            orderMenuList.append(orderedMenu.getOrderedMenu()).append(" ").
                    append(orderCount).append(ReportSetting.MENU_UNIT.getSetting()).append("\n");
        }
        return orderMenuList.toString();
    }

    public void putOrderMenuList(Order order) {
        benefitReport.put(BenefitReportInfo.ORDER_MENU_LIST.getPhrase(), getOrderMenuList(order));
    }

    public void putTotalOrderAmount(Order order) {
        benefitReport.put(BenefitReportInfo.TOTAL_ORDER_AMOUNT.getPhrase(),
                Parser.toThousandUnitMoney(order.getTotalOrderAmount()) + "\n");
    }

    private String getPresentMenu(int totalDiscount) {
        if (totalDiscount == 0) {
            return getNothingPhrase();
        }
        return PresentEventInfo.CHAMPAGNE_EVENT.getPresent() + " " +
                PresentEventInfo.CHAMPAGNE_EVENT.getCount() + ReportSetting.MENU_UNIT.getSetting() + "\n";
    }

    public void putPresentMenu(int totalDiscount) {
        benefitReport.put(BenefitReportInfo.PRESENT_MENU.getPhrase(), getPresentMenu(totalDiscount));
    }

    private String getEventHistory(String eventHistory) {
        if (eventHistory.isBlank()) {
            return getNothingPhrase();
        }
        return eventHistory;
    }

    public void putEventHistory(String eventHistory) {
        benefitReport.put(BenefitReportInfo.EVENT_HISTORY.getPhrase(), getEventHistory(eventHistory));
    }

    private String getTotalBenefitAmount(int totalBenefitAmount) {
        String totalBenefitAmountResult = Parser.toThousandUnitMoney(totalBenefitAmount) + "\n";
        if (totalBenefitAmount == 0) {
            return totalBenefitAmountResult;
        }
        return ReportSetting.MINUS.getSetting() + totalBenefitAmountResult;
    }

    public void putTotalBenefitAmount(int totalBenefitAmount) {
        benefitReport.put(BenefitReportInfo.TOTAL_BENEFIT_AMOUNT.getPhrase(), getTotalBenefitAmount(totalBenefitAmount));
    }

    public void putPaymentAmount(int totalPaymentAmount) {
        benefitReport.put(BenefitReportInfo.PAYMENT_AMOUNT.getPhrase(),
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
        benefitReport.put(BenefitReportInfo.EVENT_BADGE.getPhrase(), getEventBadge(totalBenefitAmount));
    }

    private String getNothingPhrase() {
        return ReportSetting.NOTHING.getSetting() + "\n";
    }

    public Map<String, String> getBenefitReport() {
        return benefitReport;
    }
}
