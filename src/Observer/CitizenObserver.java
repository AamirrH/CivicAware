package Observer;

import Bridge.Notification;
import Factory.ComplaintStatus;

public class CitizenObserver implements Observer {
    private final String citizenName;
    private final Notification notification;

    public CitizenObserver(String citizenName, Notification notification) {
        this.citizenName = citizenName;
        this.notification = notification;
    }

    @Override
    public void update(long complaintId, ComplaintStatus status) {
        notification.notifyUser(citizenName + ": complaint #"
                + complaintId + " is now " + status);
    }
}
