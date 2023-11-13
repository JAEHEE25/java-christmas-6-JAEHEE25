package christmas;

import christmas.controller.BenefitHistoryController;
import christmas.controller.EventDiscountController;
import christmas.domain.Order;
import christmas.domain.OrderedCount;
import christmas.domain.OrderedMenu;
import christmas.domain.VisitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BenefitHistoryTest {
    private static Order order;
    private static VisitDate visitDate;
    @BeforeEach
    void orderMenu() {
        Map<OrderedMenu, OrderedCount> orderResult = new HashMap<>();
        orderResult.put(new OrderedMenu("양송이수프", orderResult), new OrderedCount("1"));
        orderResult.put(new OrderedMenu("티본스테이크", orderResult), new OrderedCount("1"));
        orderResult.put(new OrderedMenu("크리스마스파스타", orderResult), new OrderedCount("1"));
        orderResult.put(new OrderedMenu("초코케이크", orderResult), new OrderedCount("1"));
        orderResult.put(new OrderedMenu("아이스크림", orderResult), new OrderedCount("2"));
        orderResult.put(new OrderedMenu("레드와인", orderResult), new OrderedCount("1"));
        order = new Order(orderResult);

        visitDate = new VisitDate("25");
    }

    @Test
    @DisplayName("주문한 메뉴와 개수가 표시된다.")
    void orderedMenuAndCount() {
        String expectedResult = "양송이수프 1개\n티본스테이크 1개\n크리스마스파스타 1개\n초코케이크 1개\n아이스크림 2개\n레드와인 1개\n";

        BenefitHistoryController benefitHistoryController = new BenefitHistoryController();
        String actualResult = benefitHistoryController.getOrderMenuList();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

}
