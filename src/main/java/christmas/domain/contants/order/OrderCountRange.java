package christmas.domain.contants.order;

public enum OrderCountRange {
    ORDER_COUNT_MIN(1),
    ORDER_COUNT_MAX(20);

    private final int number;

    OrderCountRange(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
