package christmas.domain;

import christmas.domain.contants.BenefitReportInfo;
import christmas.domain.contants.ReportSetting;

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
        benefitReport.put(BenefitReportInfo.ORDER_MENU_LIST.getTitle(), getOrderMenuList(order));
    }

    public Map<String, String> getBenefitReport() {
        return benefitReport;
    }
}
