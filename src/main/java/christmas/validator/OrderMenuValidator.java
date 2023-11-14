package christmas.validator;

import christmas.domain.OrderCount;
import christmas.domain.OrderMenu;
import christmas.validator.constants.ExceptionMessage;
import christmas.domain.contants.order.MenuBoard;

import java.util.Map;

public class OrderMenuValidator implements Validator<Map<OrderMenu, OrderCount>> {
    @Override
    public void validate(Map<OrderMenu, OrderCount> order) {
        if (isAllCannotOnlyOrderType(order)) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private boolean isAllCannotOnlyOrderType(Map<OrderMenu, OrderCount> order) {
        boolean isAllCannotOnlyOrderType = true;

        for (OrderMenu orderMenu : order.keySet()) {
            if (!MenuBoard.isCannotOnlyOrderType(orderMenu)) {
                isAllCannotOnlyOrderType = false;
            }
        }
        return isAllCannotOnlyOrderType;
    }
}
