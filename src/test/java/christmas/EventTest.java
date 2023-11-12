package christmas;

import christmas.controller.EventDiscountController;
import christmas.domain.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {
    @DisplayName("크리스마스 디데이에 따라 할인 금액이 100원씩 증가한다.")
    @CsvSource({"1,1000", "14,2300","25,3400"})
    @ParameterizedTest
    void selectVisitDate(String playerInput, int expectedDiscount) {
        VisitDate visitDate = new VisitDate(playerInput);
        EventDiscountController eventDiscountController = new EventDiscountController();
        int actualDiscount = eventDiscountController.calculateChristmasDiscount(visitDate);
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }
}
