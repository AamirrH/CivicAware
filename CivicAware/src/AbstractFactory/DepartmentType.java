package AbstractFactory;

public enum DepartmentType {
    ROAD("Road Department"),
    WATER("Water Department"),
    SANITATION("Sanitation Department"),
    ELECTRICAL("Electrical Department"),
    TRAFFIC("Traffic Department");

    private final String displayName;

    DepartmentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
