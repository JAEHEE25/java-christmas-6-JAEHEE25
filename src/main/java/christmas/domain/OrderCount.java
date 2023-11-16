package christmas.domain;

import christmas.util.Calculator;
import christmas.util.Parser;
import christmas.validator.OrderCountNumberFormatValidator;
import christmas.validator.OrderCountInRangeValidator;

public class OrderCount {
    private final int orderCount;

    public OrderCount(String inputCount) {
        validateNumberFormat(inputCount);
        int parsedInputCount = Parser.toInt(inputCount);
        validateCountInRange(parsedInputCount);
        orderCount = parsedInputCount;
    }

    private void validateNumberFormat(String inputCount) {
        OrderCountNumberFormatValidator orderCountNumberFormatValidator = new OrderCountNumberFormatValidator();
        orderCountNumberFormatValidator.validate(inputCount);
    }

    private void validateCountInRange(int count) {
        OrderCountInRangeValidator orderCountInRangeValidator = new OrderCountInRangeValidator();
        orderCountInRangeValidator.validate(count);
    }

    public int validateTotalCountInRange(int totalCount) {
        validateCountInRange(orderCount + totalCount);
        return orderCount + totalCount;
    }

    public int addTotalCount(int totalCount) {
        return orderCount + totalCount;
    }

    public int calculateOrderAmount(int price) {
        return Calculator.multiplication(orderCount, price);
    }

    public int getOrderCount() {
        return orderCount;
    }
}
