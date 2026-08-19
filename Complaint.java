package Factory;

public abstract class Complaint {
    private final long id;
    private final String description;
    private final String location;
    private final ComplaintType type;
    private String status;

    protected Complaint(long id, String description, String location, ComplaintType type) {
        this.id = id;
        this.description = description;
        this.location = location;
        this.type = type;
        this.status = "REPORTED";
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public ComplaintType getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type=" + type +
                ", status='" + status + '\'' +
                '}';
    }
}
