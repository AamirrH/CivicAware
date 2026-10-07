package Mediator;

import Factory.Complaint;

public class TeamColleague extends ComplaintColleague {
    private final String teamName;

    public TeamColleague(String teamName) { this.teamName = teamName; }

    @Override
    public void receive(String message, Complaint complaint) {
        System.out.println("Team " + teamName + " receives: " + message + " for #" + complaint.getId());
    }
}
