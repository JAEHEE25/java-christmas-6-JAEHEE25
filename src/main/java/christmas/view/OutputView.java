package christmas.view;

import christmas.domain.VisitDate;
import christmas.view.constants.OutputViewMessage;

public class OutputView {
    public void startEventPlanner() {
        System.out.println(OutputViewMessage.EVENT_PLANNER_START.getMessage());
    }

    public void startBenefitReport(VisitDate visitDate) {
        System.out.println(OutputViewMessage.BENEFIT_REPORT_MONTH.getMessage() +
                visitDate.getVisitDate() + OutputViewMessage.BENEFIT_REPORT.getMessage() + "\n");
    }

    public void printBenefitReport(String benefitReport) {
        System.out.println(benefitReport);
    }

    public void printExceptionMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
