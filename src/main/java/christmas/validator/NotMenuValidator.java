package christmas.validator;

import christmas.domain.contants.EventMonth;
import christmas.domain.contants.ExceptionMessage;
import christmas.domain.contants.MenuInfo;

public class NotMenuValidator implements Validator<String> {
    @Override
    public void validate(String inputMenu) {
        if (!MenuInfo.hasMenu(inputMenu)) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }
}
