package AbstractFactory;

public class ElectricalTeam implements Team {
    @Override
    public String getTeamName() {
        return "Electrical Team";
    }

    @Override
    public void assignComplaint(long complaintId) {
        System.out.println(getTeamName() + " assigned to complaint #" + complaintId);
    }
}
