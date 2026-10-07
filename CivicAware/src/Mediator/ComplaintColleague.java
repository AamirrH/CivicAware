package Mediator;

import Factory.Complaint;

public abstract class ComplaintColleague {
    protected ComplaintMediator mediator;

    void setMediator(ComplaintMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive(String message, Complaint complaint);
}
