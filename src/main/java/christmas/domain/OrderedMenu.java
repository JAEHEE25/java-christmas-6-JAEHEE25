package christmas.domain;

import christmas.domain.contants.MenuBoard;
import christmas.domain.contants.WeekEventInfo;
import christmas.validator.DuplicateMenuValidator;
import christmas.validator.InMenuBoardValidator;

import java.util.Map;

public class OrderedMenu {
    private final String orderedMenu;

    public OrderedMenu(String inputMenu, Map<OrderedMenu, OrderedCount> orderResult) {
        validateInMenu(inputMenu);
        validateDuplication(inputMenu, orderResult);
        orderedMenu = inputMenu;
    }

    private void validateInMenu(String inputMenu) {
        InMenuBoardValidator inMenuBoardValidator = new InMenuBoardValidator();
        inMenuBoardValidator.validate(inputMenu);
    }

    private void validateDuplication(String inputMenu, Map<OrderedMenu, OrderedCount> orderResult) {
        DuplicateMenuValidator duplicateMenuValidator = new DuplicateMenuValidator();
        duplicateMenuValidator.validate(inputMenu, orderResult);
    }

    public boolean isEquals(String menu) {
        return orderedMenu.equals(menu);
    }

    private String getOrderedMenuType() {
        return MenuBoard.getTypeByMenu(orderedMenu);
    }

    public boolean isWeekEventMenuType(VisitDate visitDate) {
        String orderedMenuType = getOrderedMenuType();
        return WeekEventInfo.isWeekEventMenuType(visitDate, orderedMenuType);
    }
}
