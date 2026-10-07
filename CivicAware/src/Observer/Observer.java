package Observer;

import Factory.ComplaintStatus;

public interface Observer {
    void update(long complaintId, ComplaintStatus status);
}
