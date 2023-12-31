package christmas;

import christmas.controller.OrderController;
import christmas.domain.Order;
import christmas.domain.OrderCount;
import christmas.domain.OrderMenu;
import christmas.validator.constants.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class OrderTest {
    @Test
    @DisplayName("고객은 메뉴를 주문할 수 있다.")
    void select_Menu() {
        String playerInput = "티본스테이크-2,제로콜라-1";

        Map<OrderMenu, OrderCount> expectedOrder = new HashMap<>();
        expectedOrder.put(new OrderMenu("티본스테이크", expectedOrder), new OrderCount("2"));
        expectedOrder.put(new OrderMenu("제로콜라", expectedOrder), new OrderCount("1"));

        Order actualOrder = new Order(playerInput);

        assertThat(actualOrder.getOrder()).usingRecursiveComparison().isEqualTo(expectedOrder);
    }

    @Test
    @DisplayName("고객이 메뉴판에 없는 메뉴를 입력할 경우 예외가 발생한다.")
    void order_Menu_Must_Be_In_Menu_Board() {
        String playerInput = "떡볶이-1";

        assertThatThrownBy(() -> new OrderMenu(playerInput, new HashMap<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage((ExceptionMessage.HEADER.getMessage()) +
                        ExceptionMessage.INVALID_ORDER.getMessage());
    }

    @Test
    @DisplayName("고객이 중복된 메뉴를 입력할 경우 예외가 발생한다.")
    void order_Menu_Must_Be_Not_Duplicate() {
        String playerInput = "해산물파스타-1,해산물파스타-1";

        assertThatThrownBy(() -> new OrderMenu(playerInput, new HashMap<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage((ExceptionMessage.HEADER.getMessage()) +
                        ExceptionMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("입력한 메뉴의 개수가 1 이상의 숫자가 아닐 경우 예외가 발생한다.")
    @ValueSource(strings = {"해산물파스타-A", "해산물파스타-0"})
    @ParameterizedTest
    void order_Count_Must_Be_Number_In_Range(String playerInput) {
        assertThatThrownBy(() -> new OrderCount(playerInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage((ExceptionMessage.HEADER.getMessage()) +
                        ExceptionMessage.INVALID_ORDER.getMessage());
    }

    @Test
    @DisplayName("총 주문한 메뉴의 개수가 20개를 초과할 경우 예외가 발생한다.")
    void total_Count_Must_Be_In_Range() {
        String playerInput = "해산물파스타-19,레드와인-2";
        OrderController orderController = new OrderController();

        assertThatThrownBy(() -> new Order(playerInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage((ExceptionMessage.HEADER.getMessage()) +
                        ExceptionMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("입력한 메뉴 형식이 잘못되었을 경우 예외가 발생한다.")
    @ValueSource(strings = {"해산물파스타-", "해산물파스타-초코케이크-1"})
    @ParameterizedTest
    void order_Form_Must_Be_Accurate(String playerInput) {
        OrderController orderController = new OrderController();

        assertThatThrownBy(() -> new Order(playerInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage((ExceptionMessage.HEADER.getMessage()) +
                ExceptionMessage.INVALID_ORDER.getMessage());
    }

    @Test
    @DisplayName("음료만 주문한 경우 예외가 발생한다.")
    void order_Drink_Only() {
        String playerInput = "레드와인-2,제로콜라-1";

        assertThatThrownBy(() -> new Order(playerInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage((ExceptionMessage.HEADER.getMessage()) +
                        ExceptionMessage.INVALID_ORDER.getMessage());
    }
}
