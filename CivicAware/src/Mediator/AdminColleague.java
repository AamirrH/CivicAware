package Mediator;

import Factory.Complaint;

public class AdminColleague extends ComplaintColleague {
    @Override
    public void receive(String message, Complaint complaint) {
        System.out.println("Admin receives: " + message + " for #" + complaint.getId());
    }

    public void updateTeam(String message, Complaint complaint) {
        System.out.println("Admin sends: " + message);
        mediator.send(message, complaint, this);
    }
}
