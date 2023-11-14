package christmas.domain.contants.order;

public enum OrderMenuSetting {
    CANNOT_ONLY_ORDER_TYPE("음료");

    private final String setting;

    OrderMenuSetting(String setting) {
        this.setting = setting;
    }

    public String getSetting() {
        return setting;
    }
}
