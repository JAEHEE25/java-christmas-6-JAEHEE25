package christmas.domain;

import christmas.util.Calculator;
import christmas.util.Parser;
import christmas.validator.OrderCountNumberFormatValidator;
import christmas.validator.OrderCountInRangeValidator;

public class OrderCount {
    private final int orderedCount;

    public OrderCount(String inputCount) {
        validateNumberFormat(inputCount);
        int parsedInputCount = Parser.toInt(inputCount);
        validateOrderedCount(parsedInputCount);
        orderedCount = parsedInputCount;
    }

    private void validateNumberFormat(String inputCount) {
        OrderCountNumberFormatValidator orderCountNumberFormatValidator = new OrderCountNumberFormatValidator();
        orderCountNumberFormatValidator.validate(inputCount);
    }

    private void validateOrderedCount(int count) {
        OrderCountInRangeValidator orderCountInRangeValidator = new OrderCountInRangeValidator();
        orderCountInRangeValidator.validate(count);
    }

    public int checkCountInRange(int totalCount) {
        validateOrderedCount(orderedCount + totalCount);
        return orderedCount + totalCount;
    }

    public int addTotalCount(int totalCount) {
        return orderedCount + totalCount;
    }

    public int calculateOrderAmount(int price) {
        return Calculator.multiplication(orderedCount, price);
    }

    public int getOrderedCount() {
        return orderedCount;
    }
}
