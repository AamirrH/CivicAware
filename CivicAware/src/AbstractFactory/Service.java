package AbstractFactory;

public interface Service {
    String getServiceName();

    void performService(long complaintId);
}
