package christmas.controller;

import christmas.domain.VisitDate;
import christmas.view.InputView;

public class VisitDateController {
    private final InputView inputView = new InputView();

    public String getVisitDateInput() {
        return inputView.getVisitDate();
    }

    public VisitDate createVisitDate(String playerInput) {
        return new VisitDate(playerInput);
    }
}
