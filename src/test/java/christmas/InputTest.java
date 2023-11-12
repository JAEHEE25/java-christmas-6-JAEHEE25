package christmas;

import christmas.controller.EventPlannerController;
import christmas.domain.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
public class InputTest {

    @Test
    @DisplayName("고객은 식당에 방문할 날짜를 선택할 수 있다.")
    void visitDate() {
       String playerInput = "26";
       VisitDate expectedVisitDate = new VisitDate(playerInput);

       EventPlannerController eventPlannerController = new EventPlannerController();
       VisitDate actualVisitDate = eventPlannerController.getVisitDate(playerInput);

       assertThat(actualVisitDate).usingRecursiveComparison().isEqualTo(expectedVisitDate);
    }

    @DisplayName("고객이 입력한 방문 날짜가 1 이상 31 이하의 숫자가 아닐 경우 예외가 발생한다.")
    @ValueSource(strings = {"A", "-2", "0", "45"})
    @ParameterizedTest
    void visitDateOutOfRange(String playerInput) {
        assertThatThrownBy(() -> new VisitDate(playerInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
