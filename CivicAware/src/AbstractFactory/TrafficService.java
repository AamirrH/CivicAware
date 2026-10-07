package AbstractFactory;

public class TrafficService implements Service {
    @Override
    public String getServiceName() {
        return "Traffic Service";
    }

    @Override
    public void performService(long complaintId) {
        System.out.println(getServiceName() + " started for complaint #" + complaintId);
    }
}
