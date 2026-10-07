package Mediator;

import Factory.Complaint;

public class CitizenColleague extends ComplaintColleague {
    private final String name;

    public CitizenColleague(String name) { this.name = name; }

    public void report(String message, Complaint complaint) {
        System.out.println("Citizen " + name + " sends: " + message);
        mediator.send(message, complaint, this);
    }

    @Override
    public void receive(String message, Complaint complaint) {
        System.out.println("Citizen " + name + " receives: " + message + " for #" + complaint.getId());
    }
}
