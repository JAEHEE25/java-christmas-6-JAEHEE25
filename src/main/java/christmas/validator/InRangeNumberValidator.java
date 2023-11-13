package christmas.validator;

import christmas.domain.contants.EventDate;
import christmas.domain.contants.ExceptionMessage;

public class InRangeNumberValidator implements Validator<Integer> {
    @Override
    public void validate(Integer playerInput) {
        if (playerInput < EventDate.MONTH_START_DAY.getNumber() || playerInput > EventDate.MONTH_END_DAY.getNumber()) {
            throwException(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
        }
    }
}
