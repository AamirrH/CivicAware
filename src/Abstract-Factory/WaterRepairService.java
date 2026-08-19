package AbstractFactory;

public class WaterRepairService implements Service {
    @Override
    public String getServiceName() {
        return "Water Repair Service";
    }

    @Override
    public void performService(long complaintId) {
        System.out.println(getServiceName() + " started for complaint #" + complaintId);
    }
}
