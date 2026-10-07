package AbstractFactory;

public class ElectricalService implements Service {
    @Override
    public String getServiceName() {
        return "Electrical Service";
    }

    @Override
    public void performService(long complaintId) {
        System.out.println(getServiceName() + " started for complaint #" + complaintId);
    }
}
