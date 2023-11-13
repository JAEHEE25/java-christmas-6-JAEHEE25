package christmas.domain;

import christmas.domain.contants.BenefitReportInfo;
import christmas.domain.contants.PresentEventInfo;
import christmas.domain.contants.ReportSetting;
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
        if (totalDiscount != 0) {
            return PresentEventInfo.CHAMPAGNE_EVENT.getPresent() + " " +
                    PresentEventInfo.CHAMPAGNE_EVENT.getCount() + ReportSetting.MENU_UNIT.getSetting() + "\n";
        }
        return ReportSetting.NOTHING.getSetting() + "\n";
    }

    public void putPresentMenu(int totalDiscount) {
        benefitReport.put(BenefitReportInfo.PRESENT_MENU.getPhrase(), getPresentMenu(totalDiscount));
    }

    public Map<String, String> getBenefitReport() {
        return benefitReport;
    }
}
