package christmas.domain.contants;

public enum InputSetting {
    ORDER_DELIMITER(","),
    MENU_COUNT_DELIMITER("-");

    private final String setting;

    InputSetting(String setting) {
        this.setting = setting;
    }

    public String getSetting() {
        return setting;
    }
}
