package christmas.domain.contants;

public enum ReportSetting {
    MENU_UNIT("개"),
    MONEY_UNIT("원"),
    NOTHING("없음"),
    MINUS("-"),
    COLON(":");

    private final String setting;

    ReportSetting(String setting) {
        this.setting = setting;
    }

    public String getSetting() {
        return setting;
    }
}
