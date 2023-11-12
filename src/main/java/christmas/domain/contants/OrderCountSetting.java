package christmas.domain.contants;

public enum OrderCountSetting {
    ORDER_COUNT_MIN(1),
    ORDER_COUNT_MAX(20);

    private final int setting;

    OrderCountSetting(int setting) {
        this.setting = setting;
    }

    public int getSetting() {
        return setting;
    }
}
