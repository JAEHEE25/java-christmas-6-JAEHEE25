package christmas.controller;

import christmas.domain.*;
import christmas.domain.contants.EventPeriod;
import christmas.domain.contants.PresentEventInfo;
import christmas.domain.contants.ReportSetting;
import christmas.util.Parser;
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

    public void startBenefitReport(VisitDate visitDate) {
        outputView.startBenefitReport(visitDate);
    }

    public String getChristmasEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = eventDiscountController.calculateChristmasDiscount(visitDate, order);
        if (totalDiscount == 0) {
            return "";
        }
        return getEventHistoryForm(ReportSetting.CHRISTMAS_DISCOUNT.getSetting(), totalDiscount);
    }

    public String getWeekdayEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = eventDiscountController.calculateWeekdayDiscount(visitDate, order);
        if (totalDiscount == 0) {
            return "";
        }
        return getEventHistoryForm(ReportSetting.WEEKDAY_DISCOUNT.getSetting(), totalDiscount);
    }

    public String getWeekendEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = eventDiscountController.calculateWeekendDiscount(visitDate, order);
        if (totalDiscount == 0) {
            return "";
        }
        return getEventHistoryForm(ReportSetting.WEEKEND_DISCOUNT.getSetting(), totalDiscount);
    }

    public String getSpecialEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = eventDiscountController.calculateSpecialDiscount(visitDate, order);
        if (totalDiscount == 0) {
            return "";
        }
        return getEventHistoryForm(ReportSetting.SPECIAL_DISCOUNT.getSetting(), totalDiscount);
    }

    public String getPresentEventHistory(VisitDate visitDate, Order order) {
        int totalDiscount = eventDiscountController.calculatePresentDiscount(visitDate, order);
        benefitReport.putPresentMenu(totalDiscount);

        if (totalDiscount == 0) {
            return "";
        }
        return getEventHistoryForm(ReportSetting.PRESENT_EVENT.getSetting(), totalDiscount);
    }

    private String getEventHistoryForm(String title, int totalDiscount) {
        return title + ReportSetting.COLON.getSetting() +
                ReportSetting.MINUS.getSetting() + Parser.toThousandUnitMoney(totalDiscount) + "\n";
    }

    public String setEventHistory(VisitDate visitDate, Order order) {
        StringBuilder eventHistory = new StringBuilder();
        eventHistory.append(getChristmasEventHistory(visitDate, order));
        eventHistory.append(getWeekdayEventHistory(visitDate, order));
        eventHistory.append(getWeekendEventHistory(visitDate, order));
        eventHistory.append(getSpecialEventHistory(visitDate, order));
        eventHistory.append(getPresentEventHistory(visitDate, order));

        return eventHistory.toString();
    }

    public void setBenefitReport(VisitDate visitDate, Order order) {
        benefitReport.putOrderMenuList(order);
        benefitReport.putTotalOrderAmount(order);
        benefitReport.putEventHistory(setEventHistory(visitDate, order));

        int totalBenefitAmount = eventDiscountController.calculateTotalBenefitAmount(visitDate, order);
        benefitReport.putTotalBenefitAmount(totalBenefitAmount);
        benefitReport.putPaymentAmount(order.calculatePaymentAmount(totalBenefitAmount));
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

        startBenefitReport(visitDate);
        setBenefitReport(visitDate, order);
        outputView.printBenefitReport(getBenefitReport());
    }
}
