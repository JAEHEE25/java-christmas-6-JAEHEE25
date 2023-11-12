package christmas.validator;

import christmas.domain.contants.ExceptionMessage;
import christmas.domain.contants.OrderSetting;

public class MenuCountValidator implements Validator<Integer> {
    @Override
    public void validate(Integer count) {
        if (count < OrderSetting.ORDER_COUNT_MIN.getSetting() || count > OrderSetting.ORDER_COUNT_MAX.getSetting()) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

}
