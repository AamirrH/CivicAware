package Factory;

public class WaterLeakageComplaint extends Complaint {
    public WaterLeakageComplaint(long id, String description, String location) {
        super(id, description, location, ComplaintType.WATER_LEAKAGE);
    }
}
