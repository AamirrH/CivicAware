package ChainOfResponsibility;

import AbstractFactory.DepartmentFactory;
import AbstractFactory.ElectricalDepartmentFactory;
import AbstractFactory.RoadDepartmentFactory;
import AbstractFactory.SanitationDepartmentFactory;
import AbstractFactory.Service;
import AbstractFactory.Team;
import AbstractFactory.TrafficDepartmentFactory;
import AbstractFactory.WaterDepartmentFactory;
import Bridge.NotificationSender;
import Bridge.TeamNotification;
import Factory.Complaint;
import Factory.ComplaintStatus;
import Observer.TeamObserver;

public class TeamAssignmentHandler extends Handler {
    private final NotificationSender teamNotificationSender;

    public TeamAssignmentHandler(NotificationSender teamNotificationSender) {
        this.teamNotificationSender = teamNotificationSender;
    }

    @Override
    public void handle(Complaint complaint) {
        DepartmentFactory factory = switch (complaint.getDepartment()) {
            case ROAD -> new RoadDepartmentFactory();
            case WATER -> new WaterDepartmentFactory();
            case SANITATION -> new SanitationDepartmentFactory();
            case ELECTRICAL -> new ElectricalDepartmentFactory();
            case TRAFFIC -> new TrafficDepartmentFactory();
        };

        Team team = factory.createTeam();
        Service service = factory.createService();
        complaint.setAssignedTeam(team);
        complaint.setAssignedService(service);
        complaint.addObserver(new TeamObserver(
                team.getTeamName(),
                new TeamNotification(teamNotificationSender)
        ));
        complaint.setStatus(ComplaintStatus.ASSIGNED);

        team.assignComplaint(complaint.getId());
        System.out.println(service.getServiceName() + " selected for complaint #"
                + complaint.getId());
        handleNext(complaint);
    }
}
