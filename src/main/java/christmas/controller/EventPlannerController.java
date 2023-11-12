package christmas.controller;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.view.OutputView;

import java.util.Map;

public class EventPlannerController {
    private final VisitDateController visitDateController = new VisitDateController();
    private final OrderController orderController = new OrderController();
    private final OutputView outputView = new OutputView();

    private void startEventPlanner() {
        outputView.startEventPlanner();
    }

    private VisitDate getVisitDate() {
        String playerInput = visitDateController.getVisitDateInput();
        VisitDate inputDate;

        try {
             inputDate = visitDateController.createVisitDate(playerInput);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            inputDate = getVisitDate();
        }
        return inputDate;
    }

    private Order getOrder() {
        String playerInput = orderController.getMenuAndCountInput();
        Map<String, Integer> orderResult;
        Order order;

        try {
            orderResult = orderController.takeOrder(playerInput);
            order = orderController.createOrder(orderResult);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            order = getOrder();
        }
        return order;
    }

    public void proceedEvent() {
        startEventPlanner();
        getVisitDate();
        getOrder();
    }
}
