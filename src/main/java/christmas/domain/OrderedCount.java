package christmas.domain;

import christmas.util.Calculator;
import christmas.util.Parser;
import christmas.validator.CountNumberFormatValidator;
import christmas.validator.OrderedCountValidator;

public class OrderedCount {
    private final int orderedCount;

    public OrderedCount(String inputCount) {
        validateNumberFormat(inputCount);
        int parsedInputCount = Parser.toInt(inputCount);
        validateOrderedCount(parsedInputCount);
        orderedCount = parsedInputCount;
    }

    private void validateNumberFormat(String inputCount) {
        CountNumberFormatValidator countNumberFormatValidator = new CountNumberFormatValidator();
        countNumberFormatValidator.validate(inputCount);
    }

    private void validateOrderedCount(int count) {
        OrderedCountValidator orderedCountValidator = new OrderedCountValidator();
        orderedCountValidator.validate(count);
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
