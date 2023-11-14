package christmas.domain;

import christmas.util.Calculator;
import christmas.validator.OrderMenuValidator;

import java.util.Map;

public class Order {
    private final Map<OrderedMenu, OrderedCount> order;
    private int totalOrderAmount;

    public Order(Map<OrderedMenu, OrderedCount> inputOrder) {
        validateOrderMenu(inputOrder);
        order = inputOrder;
        totalOrderAmount = calculateToTalOrderAmount();
    }

    public void validateOrderMenu(Map<OrderedMenu, OrderedCount> inputOrder) {
        OrderMenuValidator orderMenuValidator = new OrderMenuValidator();
        orderMenuValidator.validate(inputOrder);
    }

    public int getWeekEventMenuCount(VisitDate visitDate) {
        int weekEventMenuCount = 0;

        for (OrderedMenu orderedMenu : order.keySet()) {
            if (orderedMenu.isWeekEventMenuType(visitDate)) {
                OrderedCount orderedCount = order.get(orderedMenu);
                weekEventMenuCount = orderedCount.addTotalCount(weekEventMenuCount);
            }
        }
        return weekEventMenuCount;
    }

    public int calculateToTalOrderAmount() {
        for (OrderedMenu orderedMenu : order.keySet()) {
            int price = orderedMenu.getOrderedMenuPrice();
            OrderedCount orderedCount = order.get(orderedMenu);
            totalOrderAmount += orderedCount.calculateOrderAmount(price);
        }
        return totalOrderAmount;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public boolean isMeetCriteria(int moneyCriteria) {
        return totalOrderAmount >= moneyCriteria;
    }

    public Map<OrderedMenu, OrderedCount> getOrder() {
        return order;
    }

    public int calculatePaymentAmount(int totalBenefitAmount) {
        return Calculator.minus(totalOrderAmount, totalBenefitAmount);
    }
}
