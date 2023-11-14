package christmas.domain.contants;

import java.util.Arrays;
import java.util.Optional;

public enum EventBadge {
    SANTA_BADGE(20000, "산타"),
    TREE_BADGE(10000, "트리"),
    STAR_BADGE(5000, "별");

    private final int moneyCriteria;
    private final String badgeName;

    EventBadge(int moneyCriteria, String badgeName) {
        this.moneyCriteria = moneyCriteria;
        this.badgeName = badgeName;
    }

    public int getMoneyCriteria() {
        return moneyCriteria;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public static String getBadgeNameByBenefitAmount(int totalBenefitAmount) {
        Optional<EventBadge> eventBadge = Arrays.stream(EventBadge.values())
                .filter(badge -> totalBenefitAmount >= badge.moneyCriteria)
                .findAny();

        if (eventBadge.isPresent()) {
            return eventBadge.get().badgeName;
        }
        return "";
    }
}
