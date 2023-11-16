package christmas.validator;

import christmas.validator.constants.ExceptionMessage;
import christmas.domain.contants.order.OrderCountRange;

public class OrderCountInRangeValidator implements Validator<Integer> {
    @Override
    public void validate(Integer count) {
        if (count < OrderCountRange.ORDER_COUNT_MIN.getNumber() || count > OrderCountRange.ORDER_COUNT_MAX.getNumber()) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

}
