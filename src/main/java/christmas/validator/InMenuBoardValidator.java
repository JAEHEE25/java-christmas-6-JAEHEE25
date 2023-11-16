package christmas.validator;

import christmas.validator.constants.ExceptionMessage;
import christmas.domain.contants.order.MenuBoard;

public class InMenuBoardValidator implements Validator<String> {
    @Override
    public void validate(String inputMenu) {
        if (!MenuBoard.hasMenu(inputMenu)) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }
}
