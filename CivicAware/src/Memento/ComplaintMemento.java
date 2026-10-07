package Memento;

import AbstractFactory.DepartmentType;
import AbstractFactory.Service;
import AbstractFactory.Team;
import Factory.ComplaintStatus;
import Factory.EscalationLevel;
import Factory.SeverityLevel;

import java.time.LocalDateTime;

public final class ComplaintMemento {
    private final ComplaintStatus status;
    private final boolean duplicate;
    private final int riskScore;
    private final SeverityLevel severity;
    private final DepartmentType department;
    private final Team assignedTeam;
    private final Service assignedService;
    private final LocalDateTime resolutionDeadline;
    private final EscalationLevel escalationLevel;

    public ComplaintMemento(ComplaintStatus status, boolean duplicate, int riskScore,
                            SeverityLevel severity, DepartmentType department,
                            Team assignedTeam, Service assignedService,
                            LocalDateTime resolutionDeadline, EscalationLevel escalationLevel) {
        this.status = status;
        this.duplicate = duplicate;
        this.riskScore = riskScore;
        this.severity = severity;
        this.department = department;
        this.assignedTeam = assignedTeam;
        this.assignedService = assignedService;
        this.resolutionDeadline = resolutionDeadline;
        this.escalationLevel = escalationLevel;
    }

    public ComplaintStatus getStatus() { return status; }
    public boolean isDuplicate() { return duplicate; }
    public int getRiskScore() { return riskScore; }
    public SeverityLevel getSeverity() { return severity; }
    public DepartmentType getDepartment() { return department; }
    public Team getAssignedTeam() { return assignedTeam; }
    public Service getAssignedService() { return assignedService; }
    public LocalDateTime getResolutionDeadline() { return resolutionDeadline; }
    public EscalationLevel getEscalationLevel() { return escalationLevel; }
}
