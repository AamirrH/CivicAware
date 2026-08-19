package Bridge;

public class TeamNotification extends Notification {
    public TeamNotification(NotificationSender sender) {
        super(sender);
    }

    @Override
    public void notifyUser(String message) {
        sender.send("Team notification: " + message);
    }
}
