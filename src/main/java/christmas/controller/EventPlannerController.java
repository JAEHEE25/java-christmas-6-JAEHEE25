package christmas.controller;

import christmas.domain.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private String getVisitDateInput() {
        outputView.startEventPlanner();
        return inputView.getVisitDate();
    }

    public VisitDate getVisitDate(String playerInput) {
        return new VisitDate(playerInput);
    }
}
