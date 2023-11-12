package christmas.controller;

import christmas.domain.contants.InputSetting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderController {
    public Map<String, Integer> takeOrder(String playerInput) {
        Map<String, Integer> orderResult = new HashMap<>();
        List<String> orders = toOrderList(playerInput);

        for (String order : orders) {
            String[] menuAndCount = toMenuAndCountArray(order);
            orderResult.put(menuAndCount[0], toInt(menuAndCount[1]));
        }
        return orderResult;
    }

    private List<String> toOrderList(String playerInput) {
        return Arrays.stream(playerInput.split(InputSetting.ORDER_DELIMITER.getSetting())).toList();
    }

    private String[] toMenuAndCountArray(String order) {
        return order.split(InputSetting.MENU_COUNT_DELIMITER.getSetting());
    }

    private int toInt(String value) {
        return Integer.parseInt(value);
    }
}
