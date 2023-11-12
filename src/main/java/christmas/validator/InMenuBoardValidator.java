package christmas.validator;

import christmas.domain.contants.ExceptionMessage;
import christmas.domain.contants.MenuBoard;

public class InMenuBoardValidator implements Validator<String> {
    @Override
    public void validate(String inputMenu) {
        if (!MenuBoard.hasMenu(inputMenu)) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }
}
