package christmas;

import christmas.controller.VisitDateController;
import christmas.domain.VisitDate;
import christmas.validator.constants.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VisitDateTest {
    @Test
    @DisplayName("고객은 식당에 방문할 날짜를 선택할 수 있다.")
    void select_Visit_Date() {
        String playerInput = "26";
        VisitDate expectedVisitDate = new VisitDate("26");

        VisitDateController visitDateController = new VisitDateController();
        VisitDate actualVisitDate = visitDateController.createVisitDate(playerInput);

        assertThat(actualVisitDate).usingRecursiveComparison().isEqualTo(expectedVisitDate);
    }

    @DisplayName("고객이 입력한 방문 날짜가 1 이상 31 이하의 숫자가 아닐 경우 예외가 발생한다.")
    @ValueSource(strings = {"A", "-2", "0", "32"})
    @ParameterizedTest
    void visit_Date_Must_Be_In_Range(String playerInput) {
        assertThatThrownBy(() -> new VisitDate(playerInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage((ExceptionMessage.HEADER.getMessage()) +
                        ExceptionMessage.INVALID_VISIT_DATE.getMessage());
    }
}
