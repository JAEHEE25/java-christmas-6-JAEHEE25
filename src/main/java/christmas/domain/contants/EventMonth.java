package christmas.domain.contants;

public enum EventMonth {
    EVENT_MONTH(12);

    private final int month;

    EventMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }
}
