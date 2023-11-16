package christmas.domain;

import christmas.domain.contants.order.MenuBoard;
import christmas.domain.contants.eventInfo.WeekEventInfo;
import christmas.validator.DuplicateOrderMenuValidator;
import christmas.validator.InMenuBoardValidator;

import java.util.Map;

public class OrderMenu {
    private final String orderMenu;

    public OrderMenu(String inputMenu, Map<OrderMenu, OrderCount> orderResult) {
        validateInMenu(inputMenu);
        validateDuplication(inputMenu, orderResult);
        orderMenu = inputMenu;
    }

    private void validateInMenu(String inputMenu) {
        InMenuBoardValidator inMenuBoardValidator = new InMenuBoardValidator();
        inMenuBoardValidator.validate(inputMenu);
    }

    private void validateDuplication(String inputMenu, Map<OrderMenu, OrderCount> orderResult) {
        DuplicateOrderMenuValidator duplicateOrderMenuValidator = new DuplicateOrderMenuValidator();
        duplicateOrderMenuValidator.validate(inputMenu, orderResult);
    }

    public String getOrderMenu() {
        return orderMenu;
    }

    private String getOrderedMenuType() {
        return MenuBoard.getTypeByMenu(orderMenu);
    }

    public int getOrderedMenuPrice() {
        return MenuBoard.getPriceByMenu(orderMenu);
    }

    public boolean isWeekEventMenuType(VisitDate visitDate) {
        String orderedMenuType = getOrderedMenuType();
        return WeekEventInfo.isWeekEventMenuType(visitDate, orderedMenuType);
    }

    public boolean isEquals(String menu) {
        return orderMenu.equals(menu);
    }
}
