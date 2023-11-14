package christmas.controller;

import christmas.domain.Order;
import christmas.domain.OrderCount;
import christmas.domain.OrderMenu;
import christmas.util.Parser;
import christmas.validator.OrderFormValidator;
import christmas.view.InputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderController {
    private final InputView inputView = new InputView();
    private int totalCount = 0;

    public String getMenuAndCountInput() {
        return inputView.getMenuAndCount();
    }

    public Map<OrderMenu, OrderCount> takeOrder(String playerInput) {
        Map<OrderMenu, OrderCount> orderResult = new HashMap<>();
        List<String> orders = Parser.toOrderList(playerInput);

        for (String order : orders) {
            String[] menuAndCount = Parser.toMenuAndCountArray(order);
            validateOrderForm(menuAndCount);

            OrderMenu orderMenu = new OrderMenu(menuAndCount[0], orderResult);
            OrderCount orderCount = new OrderCount(menuAndCount[1]);
            totalCount = orderCount.checkCountInRange(totalCount);

            orderResult.put(orderMenu, orderCount);
        }
        return orderResult;
    }

    public Order createOrder(Map<OrderMenu, OrderCount> orderResult) {
        return new Order(orderResult);
    }

    private void validateOrderForm(String[] menuAndCount) {
        OrderFormValidator orderFormValidator = new OrderFormValidator();
        orderFormValidator.validate(menuAndCount);
    }

}
