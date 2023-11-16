package christmas.controller;

import christmas.domain.*;
import christmas.view.OutputView;

import java.util.Map;

public class EventPlannerController {
    private final VisitDateController visitDateController = new VisitDateController();
    private final OrderController orderController = new OrderController();
    private final EventDiscountController eventDiscountController = new EventDiscountController();
    private final EventHistoryController eventHistoryController = new EventHistoryController();
    private final BenefitReport benefitReport = new BenefitReport();
    private final OutputView outputView = new OutputView();

    private void startEventPlanner() {
        outputView.printEventPlannerStartMessage();
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
        Order order;

        try {
            order = orderController.createOrder(playerInput);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            order = getOrder();
        }
        return order;
    }

    public void startBenefitReport(VisitDate visitDate) {
        outputView.printBenefitReportStartMessage(visitDate);
    }

    public void setBenefitReport(VisitDate visitDate, Order order) {
        benefitReport.putOrderMenuList(order);
        benefitReport.putTotalOrderAmount(order);
        benefitReport.putPresentMenu(eventDiscountController.calculatePresentDiscount(visitDate, order));
        benefitReport.putEventHistory(eventHistoryController.getEventHistory(visitDate, order));

        int totalBenefitAmount = eventDiscountController.calculateTotalBenefitAmount(visitDate, order);
        benefitReport.putTotalBenefitAmount(totalBenefitAmount);
        benefitReport.putPaymentAmount(order.calculatePaymentAmount(totalBenefitAmount));
        benefitReport.putEventBadge(totalBenefitAmount);
    }

    public String getBenefitReport() {
        StringBuilder report = new StringBuilder();
        Map<String, String> benefitReportResult = benefitReport.getBenefitReport();

        for (String title : benefitReportResult.keySet()) {
            report.append(title).append("\n").append(benefitReportResult.get(title)).append("\n");
        }
        return report.toString();
    }

    private void showBenefitReport() {
        outputView.printBenefitReport(getBenefitReport());
    }

    public void proceedEventPlanner() {
        startEventPlanner();
        VisitDate visitDate = getVisitDate();
        Order order = getOrder();

        startBenefitReport(visitDate);
        setBenefitReport(visitDate, order);
        showBenefitReport();
    }
}
