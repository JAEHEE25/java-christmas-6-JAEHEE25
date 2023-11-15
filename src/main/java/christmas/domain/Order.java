package christmas.domain;

import christmas.util.Calculator;
import christmas.util.Parser;
import christmas.validator.LimitedMenuTypeValidator;
import christmas.validator.OrderFormValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private final Map<OrderMenu, OrderCount> order;

    public Order(String playerInput) {
        order = takeOrder(playerInput);
    }

    private Map<OrderMenu, OrderCount> takeOrder(String playerInput) {
        Map<OrderMenu, OrderCount> orderResult = new HashMap<>();
        List<String> orders = toOrderList(playerInput);
        int totalCount = 0;

        for (String order : orders) {
            String[] menuAndCount = toMenuAndCountArray(order);
            OrderMenu orderMenu = new OrderMenu(menuAndCount[0], orderResult);
            OrderCount orderCount = new OrderCount(menuAndCount[1]);
            totalCount = orderCount.validateTotalCountInRange(totalCount);

            orderResult.put(orderMenu, orderCount);
        }
        validateOrderNotOnlyLimitedMenuType(orderResult);
        return orderResult;
    }

    private List<String> toOrderList(String playerInput) {
        return Parser.toOrderList(playerInput);
    }

    private String[] toMenuAndCountArray(String order) {
        String[] menuAndCount = Parser.toMenuAndCountArray(order);
        validateOrderForm(menuAndCount);
        return menuAndCount;
    }

    private void validateOrderForm(String[] menuAndCount) {
        OrderFormValidator orderFormValidator = new OrderFormValidator();
        orderFormValidator.validate(menuAndCount);
    }

    private void validateOrderNotOnlyLimitedMenuType(Map<OrderMenu, OrderCount> inputOrder) {
        LimitedMenuTypeValidator limitedMenuTypeValidator = new LimitedMenuTypeValidator();
        limitedMenuTypeValidator.validate(inputOrder);
    }

    public int getWeekEventMenuCount(VisitDate visitDate) {
        int weekEventMenuCount = 0;

        for (OrderMenu orderMenu : order.keySet()) {
            if (orderMenu.isWeekEventMenuType(visitDate)) {
                OrderCount orderCount = order.get(orderMenu);
                weekEventMenuCount = orderCount.addTotalCount(weekEventMenuCount);
            }
        }
        return weekEventMenuCount;
    }

    private int calculateToTalOrderAmount() {
        int totalOrderAmount = 0;

        for (OrderMenu orderMenu : order.keySet()) {
            int price = orderMenu.getOrderedMenuPrice();
            OrderCount orderCount = order.get(orderMenu);
            totalOrderAmount += orderCount.calculateOrderAmount(price);
        }
        return totalOrderAmount;
    }

    public int calculatePaymentAmount(int totalBenefitAmount) {
        return Calculator.minus(calculateToTalOrderAmount(), totalBenefitAmount);
    }

    public Map<OrderMenu, OrderCount> getOrder() {
        return order;
    }

    public int getTotalOrderAmount() {
        return calculateToTalOrderAmount();
    }

    public boolean isMeetCriteria(int moneyCriteria) {
        return calculateToTalOrderAmount() >= moneyCriteria;
    }
}
