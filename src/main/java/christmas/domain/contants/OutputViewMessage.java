package christmas.domain.contants;

public enum OutputViewMessage {
    EVENT_PLANNER_START("안녕하세요! " + EventLocation.LOCATION_NAME.getLocation() +
            EventLocation.LOCATION_TYPE.getLocation() + EventMonth.EVENT_MONTH.getMonth() + "월 이벤트 플래너입니다.");

    private final String message;

    OutputViewMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
