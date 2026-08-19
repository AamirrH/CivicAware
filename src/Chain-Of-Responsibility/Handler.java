package ChainOfResponsibility;

import Factory.Complaint;

public abstract class Handler {
    private Handler next;

    public Handler setNext(Handler next) {
        this.next = next;
        return next;
    }

    public abstract void handle(Complaint complaint);

    protected void handleNext(Complaint complaint) {
        if (next != null) {
            next.handle(complaint);
        }
    }
}
