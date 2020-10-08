package DART.enums;

public enum MembershipEnum {
    BASIC("BASIC"),
    SILVER("SILVER"),
    GOLD("GOLD"),
    PLATINUM("PLATINUM");

    private final String status;

    MembershipEnum(String status) {
        this.status = status;

    }

    public String getStatus() {
        return status;

    }
}