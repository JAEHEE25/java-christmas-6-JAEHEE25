package christmas;

import christmas.domain.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

public class BenefitReportTest {
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
}
