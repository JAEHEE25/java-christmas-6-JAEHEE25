package christmas.controller;

import christmas.domain.Order;
import christmas.domain.contants.InputSetting;
import christmas.util.Parser;
import christmas.view.InputView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderController {
    private final InputView inputView = new InputView();

    public String getMenuAndCountInput() {
        return inputView.getMenuAndCount();
    }

    public Map<String, Integer> takeOrder(String playerInput) {
        Map<String, Integer> orderResult = new HashMap<>();
        List<String> orders = Parser.toOrderList(playerInput);

        for (String order : orders) {
            String[] menuAndCount = Parser.toMenuAndCountArray(order);
            orderResult.put(menuAndCount[0], Parser.toInt(menuAndCount[1]));
        }
        return orderResult;
    }

    public Order createOrder(Map<String, Integer> orderResult) {
        return new Order(orderResult);
    }
}
