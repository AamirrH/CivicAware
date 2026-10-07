package Prototype;

import Factory.CivicComplaintFactory;
import Factory.Complaint;
import Factory.ComplaintFactory;

public class ComplaintPrototype {
    private final Complaint prototype;

    public ComplaintPrototype(Complaint prototype) {
        if (prototype == null) throw new IllegalArgumentException("Prototype complaint cannot be null");
        this.prototype = prototype;
    }

    public Complaint cloneComplaint(long newId) {
        ComplaintFactory factory = new CivicComplaintFactory();
        Complaint copy = factory.createComplaint(
                prototype.getType(),
                newId,
                prototype.getDescription(),
                prototype.getLocation()
        );

        copy.setDuplicate(prototype.isDuplicate());
        copy.setRiskScore(prototype.getRiskScore());
        copy.setSeverity(prototype.getSeverity());
        copy.setDepartment(prototype.getDepartment());
        copy.setAssignedTeam(prototype.getAssignedTeam());
        copy.setAssignedService(prototype.getAssignedService());
        copy.setResolutionDeadline(prototype.getResolutionDeadline());
        while (copy.getEscalationLevel() != prototype.getEscalationLevel()) {
            copy.escalate();
        }
        copy.setStatus(prototype.getStatus());
        return copy;
    }
}
