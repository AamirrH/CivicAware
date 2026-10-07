package AbstractFactory;

public class TrafficTeam implements Team {
    @Override
    public String getTeamName() {
        return "Traffic Team";
    }

    @Override
    public void assignComplaint(long complaintId) {
        System.out.println(getTeamName() + " assigned to complaint #" + complaintId);
    }
}
