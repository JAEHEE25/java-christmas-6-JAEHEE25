package christmas.controller;

import christmas.domain.Order;
import christmas.domain.OrderedCount;
import christmas.domain.OrderedMenu;
import christmas.util.Parser;
import christmas.validator.OrderedCountValidator;
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

    public Map<OrderedMenu, OrderedCount> takeOrder(String playerInput) {
        Map<OrderedMenu, OrderedCount> orderResult = new HashMap<>();
        List<String> orders = Parser.toOrderList(playerInput);

        for (String order : orders) {
            String[] menuAndCount = Parser.toMenuAndCountArray(order);
            OrderedMenu orderedMenu = new OrderedMenu(menuAndCount[0]);
            OrderedCount orderedCount = new OrderedCount(menuAndCount[1]);

            totalCount = orderedCount.checkCountInRange(totalCount);
            orderResult.put(orderedMenu, orderedCount);
        }
        return orderResult;
    }

    public Order createOrder(Map<OrderedMenu, OrderedCount> orderResult) {
        return new Order(orderResult);
    }
}
