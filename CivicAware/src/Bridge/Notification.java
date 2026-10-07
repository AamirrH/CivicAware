package Bridge;

public abstract class Notification {
    protected final NotificationSender sender;

    protected Notification(NotificationSender sender) {
        if (sender == null) {
            throw new IllegalArgumentException("Notification sender cannot be null");
        }
        this.sender = sender;
    }

    public abstract void notifyUser(String message);
}
