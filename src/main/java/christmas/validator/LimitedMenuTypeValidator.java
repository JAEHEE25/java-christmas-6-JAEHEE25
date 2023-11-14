package christmas.validator;

import christmas.domain.OrderCount;
import christmas.domain.OrderMenu;
import christmas.validator.constants.ExceptionMessage;
import christmas.domain.contants.order.MenuBoard;

import java.util.Map;

public class LimitedMenuTypeValidator implements Validator<Map<OrderMenu, OrderCount>> {
    @Override
    public void validate(Map<OrderMenu, OrderCount> order) {
        if (isLimitedMenuType(order)) {
            throwException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private boolean isLimitedMenuType(Map<OrderMenu, OrderCount> order) {
        boolean isAllLimitedMenuType = true;

        for (OrderMenu orderMenu : order.keySet()) {
            if (!MenuBoard.isLimitedMenuType(orderMenu)) {
                isAllLimitedMenuType = false;
            }
        }
        return isAllLimitedMenuType;
    }
}
