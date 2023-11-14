package christmas;

import christmas.controller.EventDiscountController;
import christmas.domain.Order;
import christmas.domain.OrderCount;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {
    private static Order order;

    @BeforeEach
    public void order() {
        Map<OrderMenu, OrderCount> orderResult = new HashMap<>();
        orderResult.put(new OrderMenu("티본스테이크", orderResult), new OrderCount("2"));
        orderResult.put(new OrderMenu("초코케이크", orderResult), new OrderCount("1"));
        order = new Order(orderResult);
    }

    @DisplayName("크리스마스 디데이에 따라 할인 금액이 100원씩 증가한다.")
    @CsvSource({"1,1000", "14,2300","25,3400"})
    @ParameterizedTest
    void increase_Discount_By_Christmas_Event(String playerInput, int expectedDiscount) {
        VisitDate visitDate = new VisitDate(playerInput);

        EventDiscountController eventDiscountController = new EventDiscountController();
        int actualDiscount = eventDiscountController.calculateChristmasDiscount(visitDate, order);
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }

    @Test
    @DisplayName("평일에는 디저트 메뉴가 1개당 2023원씩 할인된다.")
    void discount_Weekday() {
        VisitDate visitDate = new VisitDate("4");
        int expectedDiscount = 2023;

        EventDiscountController eventDiscountController = new EventDiscountController();
        int actualDiscount = eventDiscountController.calculateWeekdayDiscount(visitDate, order);
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }

    @Test
    @DisplayName("주말에는 메인 메뉴가 1개당 2023원씩 할인된다.")
    void discount_Weekend() {
        VisitDate visitDate = new VisitDate("8");
        int expectedDiscount = 4046;

        EventDiscountController eventDiscountController = new EventDiscountController();
        int actualDiscount = eventDiscountController.calculateWeekendDiscount(visitDate, order);
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }

    @Test
    @DisplayName("이벤트 달력에 별이 있는 날에는 총주문 금액에서 1000원 할인된다.")
    void discount_Special_Event_Day() {
        VisitDate visitDate = new VisitDate("17");
        int expectedDiscount = 1000;

        EventDiscountController eventDiscountController = new EventDiscountController();
        int actualDiscount = eventDiscountController.calculateSpecialDiscount(visitDate, order);
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }

    @Test
    @DisplayName("할인 전 총주문 금액이 12만원 이상일 경우 샴페인 1개를 증정한다.")
    void present_Event_By_Total_Order_Amount() {
        VisitDate visitDate = new VisitDate("17");
        int expectedDiscount = 25000;

        EventDiscountController eventDiscountController = new EventDiscountController();
        int actualDiscount = eventDiscountController.calculatePresentDiscount(visitDate, order);
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }

    @Test
    @DisplayName("총혜택 금액을 계산한다.")
    void calculate_Total_Benefit_Amount() {
        VisitDate visitDate = new VisitDate("8");

        int expectedAmount = 30746;
        EventDiscountController eventDiscountController = new EventDiscountController();
        assertThat(eventDiscountController.calculateTotalBenefitAmount(visitDate, order)).isEqualTo(expectedAmount);
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액을 계산한다.")
    void calculate_payment_Amount() {
        int totalBenefitAmount = 30746;
        int expectedAmount = 94254;
        assertThat(order.calculatePaymentAmount(totalBenefitAmount)).isEqualTo(expectedAmount);
    }

}
