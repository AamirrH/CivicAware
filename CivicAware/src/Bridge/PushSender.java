package Bridge;

public class PushSender implements NotificationSender {
    @Override
    public void send(String message) {
        System.out.println("[PUSH] " + message);
    }
}
