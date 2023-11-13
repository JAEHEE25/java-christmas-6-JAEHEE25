package christmas.domain.contants;

public enum EventPeriod {
    CHRISTMAS_EVENT(1, 25),
    DEFAULT_EVENT(1, 31);

    private final int startDay;
    private final int endDay;

    EventPeriod(int startDay, int endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }
}

