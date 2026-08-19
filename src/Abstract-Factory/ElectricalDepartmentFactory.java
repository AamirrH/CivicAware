package AbstractFactory;

public class ElectricalDepartmentFactory implements DepartmentFactory {
    @Override
    public Team createTeam() {
        return new ElectricalTeam();
    }

    @Override
    public Service createService() {
        return new ElectricalService();
    }
}
