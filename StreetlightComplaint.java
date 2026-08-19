package Factory;

public class StreetlightComplaint extends Complaint {
    public StreetlightComplaint(long id, String description, String location) {
        super(id, description, location, ComplaintType.STREETLIGHT);
    }
}
