package christmas.domain;

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

    public boolean isEquals(String inputMenu) {
        return orderedMenu.equals(inputMenu);
    }
}
