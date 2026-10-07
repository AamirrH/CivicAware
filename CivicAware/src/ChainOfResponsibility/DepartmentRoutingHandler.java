package ChainOfResponsibility;

import AbstractFactory.DepartmentType;
import Factory.Complaint;

public class DepartmentRoutingHandler extends Handler {
    @Override
    public void handle(Complaint complaint) {
        DepartmentType department = switch (complaint.getType()) {
            case POTHOLE, OPEN_MANHOLE, ROAD_FLOODING -> DepartmentType.ROAD;
            case WATER_LEAKAGE -> DepartmentType.WATER;
            case GARBAGE -> DepartmentType.SANITATION;
            case STREETLIGHT -> DepartmentType.ELECTRICAL;
            case TRAFFIC_SIGNAL -> DepartmentType.TRAFFIC;
        };

        complaint.setDepartment(department);
        System.out.println("Routed to " + department.getDisplayName());
        handleNext(complaint);
    }
}
