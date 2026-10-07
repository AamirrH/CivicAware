package AbstractFactory;

public class WaterRepairTeam implements Team {
    @Override
    public String getTeamName() {
        return "Water Repair Team";
    }

    @Override
    public void assignComplaint(long complaintId) {
        System.out.println(getTeamName() + " assigned to complaint #" + complaintId);
    }
}
