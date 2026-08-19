package Observer;

import Bridge.Notification;
import Factory.ComplaintStatus;

public class TeamObserver implements Observer {
    private final String teamName;
    private final Notification notification;

    public TeamObserver(String teamName, Notification notification) {
        this.teamName = teamName;
        this.notification = notification;
    }

    @Override
    public void update(long complaintId, ComplaintStatus status) {
        notification.notifyUser(teamName + ": complaint #"
                + complaintId + " is now " + status);
    }
}
