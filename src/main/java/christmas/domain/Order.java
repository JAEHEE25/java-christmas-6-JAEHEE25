package christmas.domain;

import java.util.Map;

public class Order {
    private final Map<OrderedMenu, OrderedCount> order;

    public Order(Map<OrderedMenu, OrderedCount> order) {
        this.order = order;
    }
}
