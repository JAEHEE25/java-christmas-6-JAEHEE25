package christmas.domain.contants.order;

public enum OrderInputSetting {
    ORDER_DELIMITER(","),
    MENU_COUNT_DELIMITER("-");

    private final String setting;

    OrderInputSetting(String setting) {
        this.setting = setting;
    }

    public String getSetting() {
        return setting;
    }
}
