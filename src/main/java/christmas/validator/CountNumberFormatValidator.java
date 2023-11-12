package christmas.validator;

import christmas.domain.contants.ExceptionMessage;
import christmas.util.NumberFormatInspector;

public class CountNumberFormatValidator implements Validator<String> {
    @Override
    public void validate(String inputDate) {
        if (!NumberFormatInspector.isNumeric(inputDate)) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }
}