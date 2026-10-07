package Factory;

import AbstractFactory.DepartmentType;
import AbstractFactory.Service;
import AbstractFactory.Team;
import Observer.ComplaintSubject;
import Observer.Observer;

import java.time.LocalDateTime;

public abstract class Complaint {
    private final long id;
    private final String description;
    private final String location;
    private final ComplaintType type;
    private final ComplaintSubject complaintSubject;
    private ComplaintStatus status;
    private boolean duplicate;
    private int riskScore;
    private SeverityLevel severity;
    private DepartmentType department;
    private Team assignedTeam;
    private Service assignedService;
    private LocalDateTime resolutionDeadline;
    private EscalationLevel escalationLevel;

    protected Complaint(long id, String description, String location, ComplaintType type) {
        this.id = id;
        this.description = description;
        this.location = location;
        this.type = type;
        this.complaintSubject = new ComplaintSubject();
        this.status = ComplaintStatus.REPORTED;
        this.severity = SeverityLevel.LOW;
        this.escalationLevel = EscalationLevel.TEAM_MEMBER;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public ComplaintType getType() {
        return type;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Complaint status cannot be null");
        }

        if (this.status != status) {
            this.status = status;
            complaintSubject.notifyObservers(id, status);
        }
    }

    public void addObserver(Observer observer) {
        complaintSubject.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        complaintSubject.removeObserver(observer);
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    public SeverityLevel getSeverity() {
        return severity;
    }

    public void setSeverity(SeverityLevel severity) {
        this.severity = severity;
    }

    public DepartmentType getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentType department) {
        this.department = department;
    }

    public Team getAssignedTeam() {
        return assignedTeam;
    }

    public void setAssignedTeam(Team assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    public Service getAssignedService() {
        return assignedService;
    }

    public void setAssignedService(Service assignedService) {
        this.assignedService = assignedService;
    }

    public LocalDateTime getResolutionDeadline() {
        return resolutionDeadline;
    }

    public void setResolutionDeadline(LocalDateTime resolutionDeadline) {
        this.resolutionDeadline = resolutionDeadline;
    }

    public EscalationLevel getEscalationLevel() {
        return escalationLevel;
    }

    public void escalate() {
        escalationLevel = escalationLevel.nextLevel();
    }

    public Memento.ComplaintMemento saveState() {
        return new Memento.ComplaintMemento(
                status, duplicate, riskScore, severity, department,
                assignedTeam, assignedService, resolutionDeadline, escalationLevel
        );
    }

    public void restoreState(Memento.ComplaintMemento memento) {
        if (memento == null) {
            throw new IllegalArgumentException("Memento cannot be null");
        }
        this.duplicate = memento.isDuplicate();
        this.riskScore = memento.getRiskScore();
        this.severity = memento.getSeverity();
        this.department = memento.getDepartment();
        this.assignedTeam = memento.getAssignedTeam();
        this.assignedService = memento.getAssignedService();
        this.resolutionDeadline = memento.getResolutionDeadline();
        this.escalationLevel = memento.getEscalationLevel();
        this.setStatus(memento.getStatus());
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type=" + type +
                ", status='" + status + '\'' +
                ", riskScore=" + riskScore +
                ", severity=" + severity +
                ", department=" + department +
                '}';
    }
}
