package christmas.domain.contants.eventInfo;

import christmas.domain.VisitDate;
import christmas.util.Calculator;

import java.util.Arrays;

public enum WeekEventInfo {
    MONDAY(1, "평일", "디저트"),
    TUESDAY(2, "평일","디저트"),
    WEDNESDAY(3, "평일", "디저트"),
    THURSDAY(4, "평일", "디저트"),
    FRIDAY(5, "주말", "메인"),
    SATURDAY(6, "주말", "메인"),
    SUNDAY(7, "평일", "디저트");

    private final int day;
    private final String dayType;
    private final String menuType;
    private final int discountAmount;

    WeekEventInfo(int day, String dayType, String menuType) {
        this.day = day;
        this.dayType = dayType;
        this.menuType = menuType;
        this.discountAmount = 2023;
    }

    public int getDay() {
        return day;
    }

    public static boolean isWeekday(int visitDay) {
        return Arrays.stream(WeekEventInfo.values())
                .filter(day -> visitDay == day.getDay())
                .findAny().get().dayType.equals("평일");
    }

    public static boolean isWeekend(int visitDay) {
        return Arrays.stream(WeekEventInfo.values())
                .filter(day -> visitDay == day.getDay())
                .findAny().get().dayType.equals("주말");
    }

    public static boolean isWeekEventMenuType(VisitDate visitDate, String orderedMenuType) {
        return Arrays.stream(WeekEventInfo.values())
                .filter(day -> visitDate.isSameDay(day.getDay()))
                .findAny().get().menuType.equals(orderedMenuType);
    }

    public static int getDiscountAmountByDayWithCount(VisitDate visitDate, int orderedCount) {
        int discountAmount = Arrays.stream(WeekEventInfo.values())
                .filter(day -> visitDate.isSameDay(day.getDay()))
                .findAny().get().discountAmount;
        return Calculator.multiplication(discountAmount, orderedCount);
    }
}
