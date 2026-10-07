package Factory;

public class TrafficSignalComplaint extends Complaint {
    public TrafficSignalComplaint(long id, String description, String location) {
        super(id, description, location, ComplaintType.TRAFFIC_SIGNAL);
    }
}
