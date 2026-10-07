package Factory;

public enum EscalationLevel {
    TEAM_MEMBER,
    SUPERVISOR,
    DEPARTMENT_HEAD,
    ADMIN;

    public EscalationLevel nextLevel() {
        return switch (this) {
            case TEAM_MEMBER -> SUPERVISOR;
            case SUPERVISOR -> DEPARTMENT_HEAD;
            case DEPARTMENT_HEAD, ADMIN -> ADMIN;
        };
    }
}
