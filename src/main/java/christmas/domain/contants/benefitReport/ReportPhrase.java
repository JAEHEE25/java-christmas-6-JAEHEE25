package christmas.domain.contants.benefitReport;

public enum ReportPhrase {
    MENU_UNIT("개"),
    MONEY_UNIT("원"),
    NOTHING("없음"),
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    PRESENT_EVENT("증정 이벤트"),
    MINUS("-"),
    COLON(": ");

    private final String phrase;

    ReportPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getPhrase() {
        return phrase;
    }
}
