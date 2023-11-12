package christmas.domain.contants;

public enum EventMonth {
    EVENT_MONTH(12),
    MONTH_START_DAY(1),
    MONTH_END_DAY(31);

    private final int month;

    EventMonth(int month) {
        this.month = month;
    }

    public int getNumber() {
        return month;
    }
}
