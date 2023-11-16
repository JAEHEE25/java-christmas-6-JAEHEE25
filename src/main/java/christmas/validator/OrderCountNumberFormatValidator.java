package christmas.validator;

import christmas.validator.constants.ExceptionMessage;
import christmas.util.NumberFormatInspector;

public class OrderCountNumberFormatValidator implements Validator<String> {
    @Override
    public void validate(String inputDate) {
        if (!NumberFormatInspector.isNumeric(inputDate)) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }
}