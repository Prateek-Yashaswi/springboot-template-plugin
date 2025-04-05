package enums;

public enum Choice {
    Y("Yes"),
    N("No");

    private final String value;

    Choice(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
