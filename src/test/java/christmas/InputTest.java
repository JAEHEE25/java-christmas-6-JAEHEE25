package christmas;

import christmas.controller.EventPlannerController;
import christmas.domain.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
public class InputTest {

    @Test
    @DisplayName("고객의 방문 날짜 입력 받기")
    void visitDate() {
       String playerInput = "26";
       VisitDate expectedVisitDate = new VisitDate(playerInput);

       EventPlannerController eventPlannerController = new EventPlannerController();
       VisitDate actualVisitDate = eventPlannerController.getVisitDate(playerInput);

       assertThat(actualVisitDate).usingRecursiveComparison().isEqualTo(expectedVisitDate);
    }
}
