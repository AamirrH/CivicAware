package Mediator;

import Factory.Complaint;

import java.util.ArrayList;
import java.util.List;

public class ComplaintMediator {
    private final List<ComplaintColleague> colleagues = new ArrayList<>();

    public void register(ComplaintColleague colleague) {
        if (colleague != null && !colleagues.contains(colleague)) {
            colleagues.add(colleague);
            colleague.setMediator(this);
        }
    }

    public void send(String message, Complaint complaint, ComplaintColleague sender) {
        for (ComplaintColleague colleague : colleagues) {
            if (colleague != sender) {
                colleague.receive(message, complaint);
            }
        }
    }
}
