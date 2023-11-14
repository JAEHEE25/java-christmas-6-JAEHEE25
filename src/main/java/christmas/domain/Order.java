package christmas.domain;

import christmas.util.Calculator;
import christmas.validator.OrderMenuValidator;

import java.util.Map;

public class Order {
    private final Map<OrderMenu, OrderCount> order;
    private int totalOrderAmount;

    public Order(Map<OrderMenu, OrderCount> inputOrder) {
        validateOrderMenu(inputOrder);
        order = inputOrder;
        totalOrderAmount = calculateToTalOrderAmount();
    }

    public void validateOrderMenu(Map<OrderMenu, OrderCount> inputOrder) {
        OrderMenuValidator orderMenuValidator = new OrderMenuValidator();
        orderMenuValidator.validate(inputOrder);
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

    public int calculateToTalOrderAmount() {
        for (OrderMenu orderMenu : order.keySet()) {
            int price = orderMenu.getOrderedMenuPrice();
            OrderCount orderCount = order.get(orderMenu);
            totalOrderAmount += orderCount.calculateOrderAmount(price);
        }
        return totalOrderAmount;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public boolean isMeetCriteria(int moneyCriteria) {
        return totalOrderAmount >= moneyCriteria;
    }

    public Map<OrderMenu, OrderCount> getOrder() {
        return order;
    }

    public int calculatePaymentAmount(int totalBenefitAmount) {
        return Calculator.minus(totalOrderAmount, totalBenefitAmount);
    }
}
