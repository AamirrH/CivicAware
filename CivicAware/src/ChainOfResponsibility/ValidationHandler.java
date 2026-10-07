package ChainOfResponsibility;

import Factory.Complaint;
import Factory.ComplaintStatus;

public class ValidationHandler extends Handler {
    @Override
    public void handle(Complaint complaint) {
        boolean hasDescription = complaint.getDescription() != null
                && !complaint.getDescription().isBlank();
        boolean hasLocation = complaint.getLocation() != null
                && !complaint.getLocation().isBlank();

        if (!hasDescription || !hasLocation) {
            System.out.println("Validation failed for complaint #" + complaint.getId());
            return;
        }

        complaint.setStatus(ComplaintStatus.VALIDATED);
        System.out.println("Validation passed for complaint #" + complaint.getId());
        handleNext(complaint);
    }
}
