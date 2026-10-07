package AbstractFactory;

public class SanitationTeam implements Team {
    @Override
    public String getTeamName() {
        return "Sanitation Team";
    }

    @Override
    public void assignComplaint(long complaintId) {
        System.out.println(getTeamName() + " assigned to complaint #" + complaintId);
    }
}
