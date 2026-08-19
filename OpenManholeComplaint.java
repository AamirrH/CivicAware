package Factory;

public class OpenManholeComplaint extends Complaint {
    public OpenManholeComplaint(long id, String description, String location) {
        super(id, description, location, ComplaintType.OPEN_MANHOLE);
    }
}
