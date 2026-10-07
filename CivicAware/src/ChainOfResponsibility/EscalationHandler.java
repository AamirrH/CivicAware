package ChainOfResponsibility;

import Factory.Complaint;
import Factory.ComplaintStatus;

import java.time.LocalDateTime;

public class EscalationHandler extends Handler {
    @Override
    public void handle(Complaint complaint) {
        if (complaint.getResolutionDeadline() == null) {
            int resolutionHours = complaint.getSeverity().getResolutionHours();
            complaint.setResolutionDeadline(LocalDateTime.now().plusHours(resolutionHours));
            System.out.println("Resolution deadline set to "
                    + complaint.getResolutionDeadline());
        }

        boolean unresolved = complaint.getStatus() != ComplaintStatus.RESOLVED;
        boolean overdue = LocalDateTime.now().isAfter(complaint.getResolutionDeadline());

        if (unresolved && overdue) {
            complaint.escalate();
            complaint.setStatus(ComplaintStatus.ESCALATED);
            System.out.println("Complaint #" + complaint.getId() + " escalated to "
                    + complaint.getEscalationLevel());
        } else {
            System.out.println("Complaint #" + complaint.getId()
                    + " does not require escalation");
        }

        handleNext(complaint);
    }
}
