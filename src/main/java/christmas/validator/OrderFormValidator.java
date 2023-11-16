package christmas.validator;

import christmas.validator.constants.ExceptionMessage;

public class OrderFormValidator implements Validator<String[]> {
    @Override
    public void validate(String[] menuAndCount) {
        if (menuAndCount.length != 2) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }
}
