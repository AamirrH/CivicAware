package Factory;

public class GarbageComplaint extends Complaint {
    public GarbageComplaint(long id, String description, String location) {
        super(id, description, location, ComplaintType.GARBAGE);
    }
}
