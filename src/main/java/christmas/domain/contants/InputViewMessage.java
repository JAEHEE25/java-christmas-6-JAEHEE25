package christmas.domain.contants;

public enum InputViewMessage {
    VISIT_DATE_INPUT(EventMonth.EVENT_MONTH.getNumber() + "월 중 " + EventLocation.LOCATION_TYPE.getLocation() +
            "예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

    private final String message;

    InputViewMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
