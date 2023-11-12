package christmas.validator;

import christmas.domain.Order;
import christmas.domain.OrderedCount;
import christmas.domain.OrderedMenu;
import christmas.domain.contants.ExceptionMessage;

import java.util.Map;

public class DuplicateMenuValidator implements Validator<String> {
    @Override
    public void validate(String inputMenu) {
    }

    public void validate(String inputMenu, Map<OrderedMenu, OrderedCount> orderResult) {
        if (orderResult.keySet().stream().anyMatch(orderedMenu -> orderedMenu.hasInputMenu(inputMenu))) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }
}
