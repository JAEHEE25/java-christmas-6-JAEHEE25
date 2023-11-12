package christmas.domain.contants;

public enum EventPeriod {
    CHRISTMAS_EVENT(1, 25, 1000, 100);

    private final int startDay;
    private final int endDay;
    private final int startMoney;
    private final int discountAmount;

    EventPeriod(int startDay, int endDay, int startMoney, int discountAmount) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.startMoney = startMoney;
        this.discountAmount = discountAmount;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public int getStartMoney() {
        return startMoney;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}

