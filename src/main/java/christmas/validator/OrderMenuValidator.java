package christmas.validator;

import christmas.domain.OrderedCount;
import christmas.domain.OrderedMenu;
import christmas.validator.constants.ExceptionMessage;
import christmas.domain.contants.order.MenuBoard;

import java.util.Map;

public class OrderMenuValidator implements Validator<Map<OrderedMenu, OrderedCount>> {
    @Override
    public void validate(Map<OrderedMenu, OrderedCount> order) {
        if (isAllCannotOnlyOrderType(order)) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private boolean isAllCannotOnlyOrderType(Map<OrderedMenu, OrderedCount> order) {
        boolean isAllCannotOnlyOrderType = true;

        for (OrderedMenu orderedMenu : order.keySet()) {
            if (!MenuBoard.isCannotOnlyOrderType(orderedMenu)) {
                isAllCannotOnlyOrderType = false;
            }
        }
        return isAllCannotOnlyOrderType;
    }
}
