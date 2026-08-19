package Factory;

public class PotholeComplaint extends Complaint {
    public PotholeComplaint(long id, String description, String location) {
        super(id, description, location, ComplaintType.POTHOLE);
    }
}
