package christmas.domain.contants;

import christmas.domain.VisitDate;
import christmas.util.Calculator;

import java.util.Arrays;

public enum WeekdayEventInfo {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    SUNDAY(7);

    private final int day;
    private final String menuType;
    private final int discountAmount;

    WeekdayEventInfo(int day) {
        this.day = day;
        this.menuType = "디저트";
        this.discountAmount = 2023;
    }

    public int getDay() {
        return day;
    }

    public String getMenuType() {
        return menuType;
    }

    public static boolean isWeekday(int visitDay) {
        return Arrays.stream(WeekdayEventInfo.values())
                .anyMatch(weekday -> visitDay == weekday.getDay());
    }

    public static boolean isWeekdayEventMenuType(VisitDate visitDate, String orderedMenuType) {
        return Arrays.stream(WeekdayEventInfo.values())
                .filter(day -> visitDate.isSameDay(day.getDay()))
                .anyMatch(menuType -> orderedMenuType.equals(menuType.getMenuType()));
    }

    public static int getDiscountAmountByCount(VisitDate visitDate, int orderedCount) {
        int discountAmount = Arrays.stream(WeekdayEventInfo.values())
                .filter(day -> visitDate.isSameDay(day.getDay()))
                .findAny().get().discountAmount;
        return Calculator.multiplication(discountAmount, orderedCount);
    }
}
