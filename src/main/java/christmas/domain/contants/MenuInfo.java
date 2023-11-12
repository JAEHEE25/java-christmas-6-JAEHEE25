package christmas.domain.contants;

import java.util.Arrays;

public enum MenuInfo {
    MUSHROOM_SOUP("애피타이저", "양송이수프", 6000),
    TAPAS("애피타이저", "타파스", 5500),
    CAESAR_SALAD("애피타이저", "시저샐러드", 8000),
    T_BONE_STEAK("메인", "티본스테이크", 55000),
    PORK_RIBS("메인", "바비큐립", 54000),
    SEAFOOD_PASTA("메인", "해산물파스타", 35000),
    CHRISTMAS_PASTA("메인", "크리스마스파스타", 25000),
    CHOCOLATE_CAKE("디저트", "초코케이크", 15000),
    ICE_CREAM("디저트", "아이스크림", 5000),
    ZERO_COKE("음료", "제로콜라", 3000),
    RED_WINE("음료", "레드와인", 60000),
    CHAMPAGNE("음료", "샴페인", 25000);

    private final String type;
    private final String menu;
    private final int price;

    MenuInfo(String type, String menu, int price) {
        this.type = type;
        this.menu = menu;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getMenu() {
        return menu;
    }

    public int getPrice() {
        return price;
    }

    public static boolean hasMenu(String inputMenu) {
        return Arrays.stream(MenuInfo.values())
                .anyMatch(menu -> menu.getMenu().equals(inputMenu));
    }
}
