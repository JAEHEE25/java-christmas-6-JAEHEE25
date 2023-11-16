package christmas.validator;

import christmas.domain.contants.eventInfo.EventDate;
import christmas.validator.constants.ExceptionMessage;

public class VisitDateInRangeNumberValidator implements Validator<Integer> {
    @Override
    public void validate(Integer playerInput) {
        if (playerInput < EventDate.MONTH_START_DAY.getNumber() || playerInput > EventDate.MONTH_END_DAY.getNumber()) {
            throwException(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
        }
    }
}
