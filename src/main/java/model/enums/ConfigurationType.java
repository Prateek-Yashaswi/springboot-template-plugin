package model.enums;

public enum ConfigurationType {
    YAML("yaml"),
    PROPERTIES("properties");

    private final String extension;

    ConfigurationType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
