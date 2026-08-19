package AbstractFactory;

public class RoadRepairService implements Service {
    @Override
    public String getServiceName() {
        return "Road Repair Service";
    }

    @Override
    public void performService(long complaintId) {
        System.out.println(getServiceName() + " started for complaint #" + complaintId);
    }
}
