package christmas.view;

import christmas.domain.contants.OutputViewMessage;

public class OutputView {
    public void startEventPlanner() {
        System.out.println(OutputViewMessage.EVENT_PLANNER_START.getMessage());
    }

    public void printExceptionMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
