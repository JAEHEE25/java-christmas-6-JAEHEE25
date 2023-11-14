package christmas.util;

import christmas.domain.contants.order.OrderInputSetting;
import christmas.domain.contants.benefitReport.ReportSetting;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static int toInt(String value) {
        return Integer.parseInt(value);
    }

    public static List<String> toOrderList(String playerInput) {
        return Arrays.stream(playerInput.split(OrderInputSetting.ORDER_DELIMITER.getSetting())).toList();
    }

    public static String[] toMenuAndCountArray(String order) {
        return order.split(OrderInputSetting.MENU_COUNT_DELIMITER.getSetting());
    }

    public static String toThousandUnitMoney(int money) {
        return NumberFormat.getInstance().format(money) + ReportSetting.MONEY_UNIT.getSetting();
    }
}
