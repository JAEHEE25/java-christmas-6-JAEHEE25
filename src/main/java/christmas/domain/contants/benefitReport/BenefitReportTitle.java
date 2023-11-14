package christmas.domain.contants.benefitReport;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public enum BenefitReportTitle {
    ORDER_MENU_LIST("<주문 메뉴>"),
    TOTAL_ORDER_AMOUNT("<할인 전 총주문 금액>"),
    PRESENT_MENU("<증정 메뉴>"),
    EVENT_HISTORY("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    PAYMENT_AMOUNT("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>");
    
    private final String title;

    BenefitReportTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static Map<String, String> createBenefitReport() {
        Map<String, String> benefitReport = new LinkedHashMap<>();
        Arrays.stream(BenefitReportTitle.values())
                .forEach(title -> benefitReport.put(title.getTitle(), ""));
        return benefitReport;
    }
}
