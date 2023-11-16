package christmas.domain.contants.order;

public enum LimitedMenuType {
    LIMITED_MENU_TYPE("음료");

    private final String type;

    LimitedMenuType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
