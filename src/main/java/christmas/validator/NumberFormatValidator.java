package christmas.validator;

import christmas.domain.contants.ExceptionMessage;

public class NumberFormatValidator implements Validator<String> {
    @Override
    public void validate(String playerInput) {
        if (!isNumeric(playerInput)) {
            throwException(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
        }
    }

    private boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
