package christmas.validator;

import christmas.validator.constants.ExceptionMessage;
import christmas.domain.contants.order.OrderCountSetting;

public class OrderedCountValidator implements Validator<Integer> {
    @Override
    public void validate(Integer count) {
        if (count < OrderCountSetting.ORDER_COUNT_MIN.getSetting() || count > OrderCountSetting.ORDER_COUNT_MAX.getSetting()) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

}
