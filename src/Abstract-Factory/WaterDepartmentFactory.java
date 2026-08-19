package AbstractFactory;

public class WaterDepartmentFactory implements DepartmentFactory {
    @Override
    public Team createTeam() {
        return new WaterRepairTeam();
    }

    @Override
    public Service createService() {
        return new WaterRepairService();
    }
}
