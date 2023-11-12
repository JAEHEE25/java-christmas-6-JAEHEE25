package christmas.util;

import christmas.domain.contants.InputSetting;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static int toInt(String value) {
        return Integer.parseInt(value);
    }

    public static List<String> toOrderList(String playerInput) {
        return Arrays.stream(playerInput.split(InputSetting.ORDER_DELIMITER.getSetting())).toList();
    }

    public static String[] toMenuAndCountArray(String order) {
        return order.split(InputSetting.MENU_COUNT_DELIMITER.getSetting());
    }
}
