package AbstractFactory;

public class RoadRepairTeam implements Team {
    @Override
    public String getTeamName() {
        return "Road Repair Team";
    }

    @Override
    public void assignComplaint(long complaintId) {
        System.out.println(getTeamName() + " assigned to complaint #" + complaintId);
    }
}
