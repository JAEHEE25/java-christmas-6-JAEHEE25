package christmas.view.constants;

import christmas.domain.contants.eventInfo.EventDate;
import christmas.domain.contants.eventInfo.EventLocation;

public enum OutputViewMessage {
    BENEFIT_REPORT_MONTH(EventDate.EVENT_MONTH.getNumber() + "월 "),
    EVENT_PLANNER_START("안녕하세요! " + EventLocation.LOCATION_NAME.getLocation() +
            EventLocation.LOCATION_TYPE.getLocation() + " " + BENEFIT_REPORT_MONTH.getMessage() + "이벤트 플래너입니다."),
    BENEFIT_REPORT("일에 " + EventLocation.LOCATION_NAME.getLocation() + EventLocation.LOCATION_TYPE.getLocation()
            + "에서 받을 이벤트 혜택 미리 보기!");

    private final String message;

    OutputViewMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
