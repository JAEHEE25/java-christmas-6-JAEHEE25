package christmas.domain;

import christmas.util.Calculator;
import christmas.validator.LimitedMenuTypeValidator;

import java.util.Map;

public class Order {
    private final Map<OrderMenu, OrderCount> order;

    public Order(Map<OrderMenu, OrderCount> inputOrder) {
        validateOrderNotOnlyLimitedMenuType(inputOrder);
        order = inputOrder;
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
