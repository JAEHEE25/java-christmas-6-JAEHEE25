package christmas.domain.contants;

public enum ChristmasEventInfo {
    EVENT_MONEY(1000, 100);
    private final int startMoney;
    private final int discountAmount;

    ChristmasEventInfo(int startMoney, int discountAmount) {
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
