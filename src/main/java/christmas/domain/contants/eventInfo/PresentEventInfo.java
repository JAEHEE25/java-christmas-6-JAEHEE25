package christmas.domain.contants.eventInfo;

public enum PresentEventInfo {
    CHAMPAGNE_EVENT(120000, "샴페인", 1);

    private final int moneyCriteria;
    private final String present;
    private final int count;

    PresentEventInfo(int moneyCriteria, String present, int count) {
        this.moneyCriteria = moneyCriteria;
        this.present = present;
        this.count = count;
    }

    public int getMoneyCriteria() {
        return moneyCriteria;
    }

    public String getPresent() {
        return present;
    }

    public int getCount() {
        return count;
    }
}
