package AbstractFactory;

public class TrafficDepartmentFactory implements DepartmentFactory {
    @Override
    public Team createTeam() {
        return new TrafficTeam();
    }

    @Override
    public Service createService() {
        return new TrafficService();
    }
}
