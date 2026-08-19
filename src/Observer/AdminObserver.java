package Observer;

import Bridge.Notification;
import Factory.ComplaintStatus;

public class AdminObserver implements Observer {
    private final String adminName;
    private final Notification notification;

    public AdminObserver(String adminName, Notification notification) {
        this.adminName = adminName;
        this.notification = notification;
    }

    @Override
    public void update(long complaintId, ComplaintStatus status) {
        notification.notifyUser(adminName + ": complaint #"
                + complaintId + " is now " + status);
    }
}
