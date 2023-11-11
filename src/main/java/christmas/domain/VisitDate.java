package christmas.domain;

public class VisitDate {
    private final int visitDate;

    public VisitDate(String playerInput) {
        visitDate = toInt(playerInput);
    }

    private int toInt(String playerInput) {
        return Integer.parseInt(playerInput);
    }
}
