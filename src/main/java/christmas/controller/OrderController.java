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

    public String getMenuAndCountInput() {
        return inputView.getMenuAndCount();
    }

    public Order createOrder(String playerInput) {
        return new Order(playerInput);
    }
}
