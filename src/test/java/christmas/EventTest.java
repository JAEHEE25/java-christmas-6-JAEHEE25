package christmas;

import christmas.controller.EventDiscountController;
import christmas.domain.Order;
import christmas.domain.OrderedCount;
import christmas.domain.OrderedMenu;
import christmas.domain.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {
    @DisplayName("크리스마스 디데이에 따라 할인 금액이 100원씩 증가한다.")
    @CsvSource({"1,1000", "14,2300","25,3400"})
    @ParameterizedTest
    void christmasEvent(String playerInput, int expectedDiscount) {
        VisitDate visitDate = new VisitDate(playerInput);
        EventDiscountController eventDiscountController = new EventDiscountController();
        int actualDiscount = eventDiscountController.calculateChristmasDiscount(visitDate);
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }

    @Test
    @DisplayName("평일에는 디저트 메뉴가 1개당 2023원씩 할인된다.")
    void weekdayEvent() {
        VisitDate visitDate = new VisitDate("4");

        Map<OrderedMenu, OrderedCount> orderResult = new HashMap<>();
        orderResult.put(new OrderedMenu("티본스테이크", orderResult), new OrderedCount("2"));
        orderResult.put(new OrderedMenu("초코케이크", orderResult), new OrderedCount("1"));
        Order order = new Order(orderResult);

        int expectedDiscount = 2023;

        EventDiscountController eventDiscountController = new EventDiscountController();
        int actualDiscount = eventDiscountController.calculateWeekdayDiscount(visitDate, order);
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }

    @Test
    @DisplayName("주말에는 메인 메뉴가 1개당 2023원씩 할인된다.")
    void weekendEvent() {
        VisitDate visitDate = new VisitDate("8");

        Map<OrderedMenu, OrderedCount> orderResult = new HashMap<>();
        orderResult.put(new OrderedMenu("티본스테이크", orderResult), new OrderedCount("2"));
        orderResult.put(new OrderedMenu("초코케이크", orderResult), new OrderedCount("1"));
        Order order = new Order(orderResult);

        int expectedDiscount = 4046;

        EventDiscountController eventDiscountController = new EventDiscountController();
        int actualDiscount = eventDiscountController.calculateWeekendDiscount(visitDate, order);
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }
}
