package Factory;

public class RoadFloodingComplaint extends Complaint {
    public RoadFloodingComplaint(long id, String description, String location) {
        super(id, description, location, ComplaintType.ROAD_FLOODING);
    }
}
