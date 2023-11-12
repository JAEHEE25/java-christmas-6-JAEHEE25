package christmas.controller;

import christmas.domain.Order;
import christmas.util.Parser;
import christmas.validator.CountNumberFormatValidator;
import christmas.validator.InMenuBoardValidator;
import christmas.validator.MenuCountValidator;
import christmas.validator.DateNumberFormatValidator;
import christmas.view.InputView;

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
            String menu = validateInMenu(menuAndCount[0]);
            int count = validateCount(menuAndCount[1]);
            orderResult.put(menu, count);
        }
        return orderResult;
    }

    public Order createOrder(Map<String, Integer> orderResult) {
        return new Order(orderResult);
    }

    private String validateInMenu(String inputMenu) {
        InMenuBoardValidator inMenuBoardValidator = new InMenuBoardValidator();
        inMenuBoardValidator.validate(inputMenu);
        return inputMenu;
    }

    private void validateNumberFormat(String inputCount) {
        CountNumberFormatValidator countNumberFormatValidator = new CountNumberFormatValidator();
        countNumberFormatValidator.validate(inputCount);
    }

    private int validateCount(String inputCount) {
        validateNumberFormat(inputCount);
        int count = Parser.toInt(inputCount);

        MenuCountValidator menuCountValidator = new MenuCountValidator();
        menuCountValidator.validate(count);

        return count;
    }
}
