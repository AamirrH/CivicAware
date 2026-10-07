package Bridge;

public class CitizenNotification extends Notification {
    public CitizenNotification(NotificationSender sender) {
        super(sender);
    }

    @Override
    public void notifyUser(String message) {
        sender.send("Citizen notification: " + message);
    }
}
