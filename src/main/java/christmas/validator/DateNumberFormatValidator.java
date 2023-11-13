package christmas.validator;

import christmas.domain.contants.ExceptionMessage;
import christmas.util.NumberFormatInspector;

public class DateNumberFormatValidator implements Validator<String> {
    @Override
    public void validate(String inputDate) {
        if (!NumberFormatInspector.isNumeric(inputDate)) {
            throwException(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
        }
    }
}