package christmas.controller;

import christmas.domain.*;
import christmas.domain.contants.EventPeriod;
import christmas.view.OutputView;

import java.util.Map;

public class EventPlannerController {
    private final VisitDateController visitDateController = new VisitDateController();
    private final OrderController orderController = new OrderController();
    private final EventDiscountController eventDiscountController = new EventDiscountController();
    private final BenefitReport benefitReport = new BenefitReport();
    private final OutputView outputView = new OutputView();

    private void startEventPlanner() {
        outputView.startEventPlanner();
    }

    private VisitDate getVisitDate() {
        String playerInput = visitDateController.getVisitDateInput();
        VisitDate inputDate;

        try {
             inputDate = visitDateController.createVisitDate(playerInput);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            inputDate = getVisitDate();
        }
        return inputDate;
    }

    private Order getOrder() {
        String playerInput = orderController.getMenuAndCountInput();
        Map<OrderedMenu, OrderedCount> orderResult;
        Order order;

        try {
            orderResult = orderController.takeOrder(playerInput);
            order = orderController.createOrder(orderResult);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            order = getOrder();
        }
        return order;
    }

    public void applyEventDiscount(VisitDate visitDate, Order order) {
        applyPresentEventDiscount(visitDate, order);
    }

    public void applyPresentEventDiscount(VisitDate visitDate, Order order) {
        int totalDiscount = eventDiscountController.calculatePresentDiscount(visitDate, order);
        benefitReport.putPresentMenu(totalDiscount);
    }

    public void startBenefitReport(VisitDate visitDate) {
        outputView.startBenefitReport(visitDate);
    }

    public void setBenefitReport(Order order) {
        benefitReport.putOrderMenuList(order);
        benefitReport.putTotalOrderAmount(order);
    }

    public String getBenefitReport() {
        StringBuilder report = new StringBuilder();
        Map<String, String> benefitReportResult = benefitReport.getBenefitReport();

        for (String title : benefitReportResult.keySet()) {
            report.append(title).append("\n").append(benefitReportResult.get(title)).append("\n");
        }

        return report.toString();
    }

    public void proceedEventPlanner() {
        startEventPlanner();
        VisitDate visitDate = getVisitDate();
        Order order = getOrder();

        applyEventDiscount(visitDate, order);

        startBenefitReport(visitDate);
        setBenefitReport(order);
        outputView.printBenefitReport(getBenefitReport());
    }
}
