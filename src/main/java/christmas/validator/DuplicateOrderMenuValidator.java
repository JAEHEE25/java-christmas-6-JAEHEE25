package christmas.validator;

import christmas.domain.OrderCount;
import christmas.domain.OrderMenu;
import christmas.validator.constants.ExceptionMessage;

import java.util.Map;

public class DuplicateOrderMenuValidator implements Validator<String> {
    @Override
    public void validate(String inputMenu) {
    }

    public void validate(String inputMenu, Map<OrderMenu, OrderCount> orderResult) {
        if (orderResult.keySet().stream().anyMatch(orderedMenu -> orderedMenu.isEquals(inputMenu))) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }
}
