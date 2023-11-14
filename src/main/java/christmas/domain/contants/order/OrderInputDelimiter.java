package christmas.domain.contants.order;

public enum OrderInputDelimiter {
    ORDER_DELIMITER(","),
    MENU_COUNT_DELIMITER("-");

    private final String delimiter;

    OrderInputDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
