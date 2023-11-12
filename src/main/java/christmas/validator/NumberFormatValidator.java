package christmas.validator;

import christmas.domain.contants.ExceptionMessage;
import christmas.util.Parser;

public class NumberFormatValidator implements Validator<String> {
    @Override
    public void validate(String playerInput) {
        if (!isNumeric(playerInput)) {
            throwException(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
        }
    }

    private boolean isNumeric(String value) {
        try {
            Parser.toInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
