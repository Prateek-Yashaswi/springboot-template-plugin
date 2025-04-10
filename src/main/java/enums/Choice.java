package enums;

public enum Choice {
    Y("Yes", true),
    N("No", false);

    private final String value;
    private final Boolean flag;

    Choice(String value, Boolean flag) {
        this.value = value;
        this.flag = flag;
    }

    public String getValue() {
        return value;
    }

    public Boolean getFlag() {
        return flag;
    }
}
