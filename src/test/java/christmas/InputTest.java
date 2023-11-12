package christmas;

import christmas.controller.OrderController;
import christmas.controller.VisitDateController;
import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.contants.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class InputTest {
    @Test
    @DisplayName("고객은 식당에 방문할 날짜를 선택할 수 있다.")
    void selectVisitDate() {
        String playerInput = "26";
        VisitDate expectedVisitDate = new VisitDate("26");

        VisitDateController visitDateController = new VisitDateController();
        VisitDate actualVisitDate = visitDateController.createVisitDate(playerInput);

        assertThat(actualVisitDate).usingRecursiveComparison().isEqualTo(expectedVisitDate);
    }

    @DisplayName("고객이 입력한 방문 날짜가 1 이상 31 이하의 숫자가 아닐 경우 예외가 발생한다.")
    @ValueSource(strings = {"A", "-2", "0", "32"})
    @ParameterizedTest
    void visitDateOutOfRange(String playerInput) {
        assertThatThrownBy(() -> new VisitDate(playerInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage((ExceptionMessage.HEADER.getMessage()) +
                        ExceptionMessage.INVALID_VISIT_DATE.getMessage());
    }

    @Test
    @DisplayName("고객은 메뉴를 주문할 수 있다.")
    void selectMenu() {
        String playerInput = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        Map<String, Integer> expectedOrder = new HashMap<>();
        expectedOrder.put("티본스테이크", 1);
        expectedOrder.put("바비큐립", 1);
        expectedOrder.put("초코케이크", 2);
        expectedOrder.put("제로콜라", 1);

        OrderController orderController = new OrderController();
        Map<String, Integer> actualOrder = orderController.takeOrder(playerInput);

        assertThat(actualOrder).isEqualTo(expectedOrder);
    }

    @DisplayName("고객이 메뉴판에 없는 메뉴를 입력하거나, 입력한 메뉴의 개수가 1 이상 20 이하의 숫자가 아닐 경우 예외가 발생한다.")
    @ValueSource(strings = {"떡볶이-1", "해산물파스타-A", "해산물파스타-0", "해산물파스타-21"})
    @ParameterizedTest
    void notMenu(String playerInput) {
        OrderController orderController = new OrderController();
        assertThatThrownBy(() -> orderController.takeOrder(playerInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage((ExceptionMessage.HEADER.getMessage()) +
                ExceptionMessage.INVALID_ORDER.getMessage());
    }
}
