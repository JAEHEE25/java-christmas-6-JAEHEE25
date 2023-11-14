package christmas.util;

import christmas.domain.contants.order.OrderInputDelimiter;
import christmas.domain.contants.benefitReport.ReportPhrase;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static int toInt(String value) {
        return Integer.parseInt(value);
    }

    public static List<String> toOrderList(String playerInput) {
        return Arrays.stream(playerInput.split(OrderInputDelimiter.ORDER_DELIMITER.getDelimiter())).toList();
    }

    public static String[] toMenuAndCountArray(String order) {
        return order.split(OrderInputDelimiter.MENU_COUNT_DELIMITER.getDelimiter());
    }

    public static String toThousandUnitMoney(int money) {
        return NumberFormat.getInstance().format(money) + ReportPhrase.MONEY_UNIT.getPhrase();
    }
}
