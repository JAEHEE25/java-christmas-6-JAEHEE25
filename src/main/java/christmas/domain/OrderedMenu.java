package christmas.domain;

import christmas.validator.InMenuBoardValidator;

public class OrderedMenu {
    private final String orderedMenu;

    public OrderedMenu(String inputMenu) {
        validateInMenu(inputMenu);
        orderedMenu = inputMenu;
    }

    private void validateInMenu(String inputMenu) {
        InMenuBoardValidator inMenuBoardValidator = new InMenuBoardValidator();
        inMenuBoardValidator.validate(inputMenu);
    }
}
