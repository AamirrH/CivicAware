package Bridge;

public class AdminNotification extends Notification {
    public AdminNotification(NotificationSender sender) {
        super(sender);
    }

    @Override
    public void notifyUser(String message) {
        sender.send("Admin notification: " + message);
    }
}
