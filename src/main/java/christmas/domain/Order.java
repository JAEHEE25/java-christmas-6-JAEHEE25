package christmas.domain;

import christmas.validator.OrderMenuValidator;

import java.util.Map;

public class Order {
    private final Map<OrderedMenu, OrderedCount> order;

    public Order(Map<OrderedMenu, OrderedCount> inputOrder) {
        validateOrderMenu(inputOrder);
        order = inputOrder;
    }

    public void validateOrderMenu(Map<OrderedMenu, OrderedCount> inputOrder) {
        OrderMenuValidator orderMenuValidator = new OrderMenuValidator();
        orderMenuValidator.validate(inputOrder);
    }

    public int getWeekdayEventMenuCount(VisitDate visitDate) {
        int weekdayEventMenuCount = 0;

        for (OrderedMenu orderedMenu : order.keySet()) {
            if (orderedMenu.isWeekdayEventMenuType(visitDate)) {
                OrderedCount orderedCount = order.get(orderedMenu);
                weekdayEventMenuCount = orderedCount.addTotalCount(weekdayEventMenuCount);
            }
        }
        return weekdayEventMenuCount;
    }
}
