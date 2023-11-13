package christmas.domain.contants;

public enum ReportUnit {
    MENU_UNIT("개"),
    MONEY_UNIT("원"),
    MINUS

    private final String title;

    BenefitReportInfo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
