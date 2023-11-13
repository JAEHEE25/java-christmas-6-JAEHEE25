package christmas.domain;

import christmas.domain.contants.MenuBoard;
import christmas.validator.OrderMenuValidator;

import java.util.Map;

public class Order {
    private final Map<OrderedMenu, OrderedCount> order;
    private int totalOrderAmount;

    public Order(Map<OrderedMenu, OrderedCount> inputOrder) {
        validateOrderMenu(inputOrder);
        order = inputOrder;
        totalOrderAmount = 0;
    }

    public void validateOrderMenu(Map<OrderedMenu, OrderedCount> inputOrder) {
        OrderMenuValidator orderMenuValidator = new OrderMenuValidator();
        orderMenuValidator.validate(inputOrder);
    }

    public int getWeekEventMenuCount(VisitDate visitDate) {
        int weekEventMenuCount = 0;

        for (OrderedMenu orderedMenu : order.keySet()) {
            if (orderedMenu.isWeekEventMenuType(visitDate)) {
                OrderedCount orderedCount = order.get(orderedMenu);
                weekEventMenuCount = orderedCount.addTotalCount(weekEventMenuCount);
            }
        }
        return weekEventMenuCount;
    }

    public int calculateToTalOrderAmount() {
        for (OrderedMenu orderedMenu : order.keySet()) {
            int price = orderedMenu.getOrderedMenuPrice();
            OrderedCount orderedCount = order.get(orderedMenu);
            totalOrderAmount += orderedCount.calculateOrderAmount(price);
        }
        return totalOrderAmount;
    }

    public Map<OrderedMenu, OrderedCount> getOrder() {
        return order;
    }
}
