package christmas.domain;

import christmas.validator.OrderMenuValidator;

import java.util.Map;

public class Order {
    private final Map<OrderedMenu, OrderedCount> order;

    public Order(Map<OrderedMenu, OrderedCount> inputOrder) {
        validateOnlyDrink(inputOrder);
        order = inputOrder;
    }

    public void validateOnlyDrink(Map<OrderedMenu, OrderedCount> inputOrder) {
        OrderMenuValidator orderMenuValidator = new OrderMenuValidator();
        orderMenuValidator.validate(inputOrder);
    }
}
