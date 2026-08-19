package Factory;

public interface ComplaintFactory {
    Complaint createComplaint(ComplaintType type, long id, String description, String location);
}
