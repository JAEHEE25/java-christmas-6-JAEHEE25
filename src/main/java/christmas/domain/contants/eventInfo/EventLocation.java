package christmas.domain.contants.eventInfo;

public enum EventLocation {
    LOCATION_NAME("우테코 "),
    LOCATION_TYPE("식당");

    private final String location;

    EventLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
