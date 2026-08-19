package Singleton;

import Factory.Complaint;
import Factory.ComplaintStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class IncidentManager {
    private static final IncidentManager INSTANCE = new IncidentManager();

    private final Map<Long, Complaint> complaints = new LinkedHashMap<>();

    private IncidentManager() {
    }

    public static IncidentManager getInstance() {
        return INSTANCE;
    }

    public synchronized void addComplaint(Complaint complaint) {
        if (complaint == null) {
            throw new IllegalArgumentException("Complaint cannot be null");
        }
        complaints.putIfAbsent(complaint.getId(), complaint);
    }

    public synchronized Complaint getComplaint(long complaintId) {
        return complaints.get(complaintId);
    }

    public synchronized List<Complaint> getAllComplaints() {
        return new ArrayList<>(complaints.values());
    }

    public synchronized void updateComplaintStatus(
            long complaintId,
            ComplaintStatus newStatus
    ) {
        Complaint complaint = complaints.get(complaintId);
        if (complaint == null) {
            throw new IllegalArgumentException(
                    "Complaint #" + complaintId + " was not found"
            );
        }
        complaint.setStatus(newStatus);
    }

    public synchronized boolean isDuplicate(Complaint complaint) {
        for (Complaint existing : complaints.values()) {
            if (existing == complaint) {
                continue;
            }

            boolean sameId = existing.getId() == complaint.getId();
            boolean sameDetails = createSignature(existing)
                    .equals(createSignature(complaint));

            if (sameId || sameDetails) {
                return true;
            }
        }
        return false;
    }

    public synchronized List<Complaint> getEscalatedComplaints() {
        List<Complaint> escalatedComplaints = new ArrayList<>();
        for (Complaint complaint : complaints.values()) {
            if (complaint.getStatus() == ComplaintStatus.ESCALATED) {
                escalatedComplaints.add(complaint);
            }
        }
        return escalatedComplaints;
    }

    public synchronized String getEscalationInformation(long complaintId) {
        Complaint complaint = complaints.get(complaintId);
        if (complaint == null) {
            return "Complaint #" + complaintId + " was not found";
        }

        return "Complaint #" + complaintId
                + ": level=" + complaint.getEscalationLevel()
                + ", severity=" + complaint.getSeverity()
                + ", deadline=" + complaint.getResolutionDeadline();
    }

    private String createSignature(Complaint complaint) {
        return (complaint.getType() + "|" + complaint.getDescription()
                + "|" + complaint.getLocation()).toLowerCase(Locale.ROOT);
    }
}
