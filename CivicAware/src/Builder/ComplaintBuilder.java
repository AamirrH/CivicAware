package Builder;

import Factory.CivicComplaintFactory;
import Factory.Complaint;
import Factory.ComplaintFactory;
import Factory.ComplaintType;

public class ComplaintBuilder {
    private long id;
    private String description;
    private String location;
    private ComplaintType type;

    public ComplaintBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public ComplaintBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ComplaintBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public ComplaintBuilder setType(ComplaintType type) {
        this.type = type;
        return this;
    }

    public Complaint build() {
        if (id <= 0) throw new IllegalArgumentException("Complaint id must be positive");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("Description is required");
        if (location == null || location.isBlank()) throw new IllegalArgumentException("Location is required");
        if (type == null) throw new IllegalArgumentException("Complaint type is required");

        ComplaintFactory factory = new CivicComplaintFactory();
        return factory.createComplaint(type, id, description, location);
    }
}
