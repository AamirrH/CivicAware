package Factory;

public enum SeverityLevel {
    LOW(48),
    MEDIUM(24),
    HIGH(12),
    CRITICAL(4);

    private final int resolutionHours;

    SeverityLevel(int resolutionHours) {
        this.resolutionHours = resolutionHours;
    }

    public int getResolutionHours() {
        return resolutionHours;
    }

    public static SeverityLevel fromRiskScore(int riskScore) {
        if (riskScore <= 30) {
            return LOW;
        }
        if (riskScore <= 60) {
            return MEDIUM;
        }
        if (riskScore <= 80) {
            return HIGH;
        }
        return CRITICAL;
    }
}
