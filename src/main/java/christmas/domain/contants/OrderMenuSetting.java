package christmas.domain.contants;

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
