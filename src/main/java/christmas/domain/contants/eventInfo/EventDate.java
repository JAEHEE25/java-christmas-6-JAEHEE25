package christmas.domain.contants.eventInfo;

public enum EventDate {
    EVENT_YEAR(2023),
    EVENT_MONTH(12),
    MONTH_START_DAY(1),
    MONTH_END_DAY(31);

    private final int number;

    EventDate(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
