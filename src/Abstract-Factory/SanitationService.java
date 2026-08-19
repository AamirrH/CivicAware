package AbstractFactory;

public class SanitationService implements Service {
    @Override
    public String getServiceName() {
        return "Sanitation Service";
    }

    @Override
    public void performService(long complaintId) {
        System.out.println(getServiceName() + " started for complaint #" + complaintId);
    }
}
