package christmas.validator;

import christmas.domain.contants.ExceptionMessage;
import christmas.domain.contants.OrderCountSetting;

public class OrderedCountValidator implements Validator<Integer> {
    @Override
    public void validate(Integer count) {
        if (count < OrderCountSetting.ORDER_COUNT_MIN.getSetting() || count > OrderCountSetting.ORDER_COUNT_MAX.getSetting()) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

}
