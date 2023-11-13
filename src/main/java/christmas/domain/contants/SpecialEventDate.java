package christmas.domain.contants;

import christmas.domain.VisitDate;
import christmas.util.Calculator;

import java.util.Arrays;

public enum SpecialEventDate {
    THIRD(3),
    TENTH(10),
    SEVENTEENTH(17),
    TWENTY_FOURTH(24),
    TWENTY_FIFTH(25),
    THIRTY_FIRST(31);

    private final int date;
    private final int discountAmount;

    SpecialEventDate(int date) {
        this.date = date;
        this.discountAmount = 1000;
    }

    public int getDate() {
        return date;
    }

    public static boolean isSpecialEventDate(int visitDate) {
        return Arrays.stream(SpecialEventDate.values())
                .anyMatch(date -> date.getDate() == visitDate);
    }

    public static int getDiscountAmountByDate(VisitDate visitDate) {
        return Arrays.stream(SpecialEventDate.values())
                .filter(date -> visitDate.isSameDate(date.getDate()))
                .findAny().get().discountAmount;
    }
}
