package AbstractFactory;

public class SanitationDepartmentFactory implements DepartmentFactory {
    @Override
    public Team createTeam() {
        return new SanitationTeam();
    }

    @Override
    public Service createService() {
        return new SanitationService();
    }
}
