package christmas.domain;

import christmas.domain.contants.order.MenuBoard;
import christmas.domain.contants.eventInfo.WeekEventInfo;
import christmas.validator.DuplicateOrderMenuValidator;
import christmas.validator.InMenuBoardValidator;

import java.util.Map;

public class OrderMenu {
    private final String orderedMenu;

    public OrderMenu(String inputMenu, Map<OrderMenu, OrderCount> orderResult) {
        validateInMenu(inputMenu);
        validateDuplication(inputMenu, orderResult);
        orderedMenu = inputMenu;
    }

    private void validateInMenu(String inputMenu) {
        InMenuBoardValidator inMenuBoardValidator = new InMenuBoardValidator();
        inMenuBoardValidator.validate(inputMenu);
    }

    private void validateDuplication(String inputMenu, Map<OrderMenu, OrderCount> orderResult) {
        DuplicateOrderMenuValidator duplicateOrderMenuValidator = new DuplicateOrderMenuValidator();
        duplicateOrderMenuValidator.validate(inputMenu, orderResult);
    }

    public boolean isEquals(String menu) {
        return orderedMenu.equals(menu);
    }

    private String getOrderedMenuType() {
        return MenuBoard.getTypeByMenu(orderedMenu);
    }

    public int getOrderedMenuPrice() {
        return MenuBoard.getPriceByMenu(orderedMenu);
    }

    public boolean isWeekEventMenuType(VisitDate visitDate) {
        String orderedMenuType = getOrderedMenuType();
        return WeekEventInfo.isWeekEventMenuType(visitDate, orderedMenuType);
    }

    public String getOrderedMenu() {
        return orderedMenu;
    }
}
