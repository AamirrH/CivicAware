package AbstractFactory;

public class RoadDepartmentFactory implements DepartmentFactory {
    @Override
    public Team createTeam() {
        return new RoadRepairTeam();
    }

    @Override
    public Service createService() {
        return new RoadRepairService();
    }
}
