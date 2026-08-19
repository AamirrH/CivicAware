package Factory;

public class CivicComplaintFactory implements ComplaintFactory {
    @Override
    public Complaint createComplaint(
            ComplaintType type,
            long id,
            String description,
            String location
    ) {
        if (type == null) {
            throw new IllegalArgumentException("Complaint type cannot be null");
        }

        return switch (type) {
            case POTHOLE -> new PotholeComplaint(id, description, location);
            case WATER_LEAKAGE -> new WaterLeakageComplaint(id, description, location);
            case GARBAGE -> new GarbageComplaint(id, description, location);
            case STREETLIGHT -> new StreetlightComplaint(id, description, location);
            case TRAFFIC_SIGNAL -> new TrafficSignalComplaint(id, description, location);
            case OPEN_MANHOLE -> new OpenManholeComplaint(id, description, location);
            case ROAD_FLOODING -> new RoadFloodingComplaint(id, description, location);
        };
    }
}
