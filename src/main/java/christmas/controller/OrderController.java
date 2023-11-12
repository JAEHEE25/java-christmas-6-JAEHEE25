package christmas.controller;

import christmas.domain.Order;
import christmas.util.Parser;
import christmas.validator.CountNumberFormatValidator;
import christmas.validator.InMenuBoardValidator;
import christmas.validator.MenuCountValidator;
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

    public Map<String, Integer> takeOrder(String playerInput) {
        Map<String, Integer> orderResult = new HashMap<>();
        List<String> orders = Parser.toOrderList(playerInput);

        for (String order : orders) {
            String[] menuAndCount = getMenuAndCount(order);
            String menu = menuAndCount[0];
            int count = Parser.toInt(menuAndCount[1]);

            validateCount(count);
            checkTotalCount(count);

            orderResult.put(menu, count);
        }
        return orderResult;
    }

    public Order createOrder(Map<String, Integer> orderResult) {
        return new Order(orderResult);
    }

    public String[] getMenuAndCount(String order) {
        String[] menuAndCount = Parser.toMenuAndCountArray(order);
        validateInMenu(menuAndCount[0]);
        validateNumberFormat(menuAndCount[1]);
        return menuAndCount;
    }

    private void checkTotalCount(int inputCount) {
        totalCount += inputCount;
        validateCount(totalCount);
    }

    private void validateInMenu(String inputMenu) {
        InMenuBoardValidator inMenuBoardValidator = new InMenuBoardValidator();
        inMenuBoardValidator.validate(inputMenu);
    }

    private void validateNumberFormat(String inputCount) {
        CountNumberFormatValidator countNumberFormatValidator = new CountNumberFormatValidator();
        countNumberFormatValidator.validate(inputCount);
    }

    private void validateCount(int count) {
        MenuCountValidator menuCountValidator = new MenuCountValidator();
        menuCountValidator.validate(count);
    }
}
