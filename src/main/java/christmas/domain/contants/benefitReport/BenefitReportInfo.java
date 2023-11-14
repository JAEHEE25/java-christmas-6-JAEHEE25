package christmas.domain.contants.benefitReport;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public enum BenefitReportInfo {
    ORDER_MENU_LIST("<주문 메뉴>"),
    TOTAL_ORDER_AMOUNT("<할인 전 총주문 금액>"),
    PRESENT_MENU("<증정 메뉴>"),
    EVENT_HISTORY("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    PAYMENT_AMOUNT("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>");
    
    private final String phrase;

    BenefitReportInfo(String phrase) {
        this.phrase = phrase;
    }

    public String getPhrase() {
        return phrase;
    }

    public static Map<String, String> createBenefitReport() {
        Map<String, String> benefitReport = new LinkedHashMap<>();
        Arrays.stream(BenefitReportInfo.values())
                .forEach(title -> benefitReport.put(title.getPhrase(), ""));
        return benefitReport;
    }
}
