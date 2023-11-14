package christmas.domain.contants.eventInfo;

public enum ChristmasEventAmount {
    EVENT_AMOUNT(1000, 100);
    private final int startMoney;
    private final int discountAmount;

    ChristmasEventAmount(int startMoney, int discountAmount) {
        this.startMoney = startMoney;
        this.discountAmount = discountAmount;
    }

    public int getStartMoney() {
        return startMoney;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
