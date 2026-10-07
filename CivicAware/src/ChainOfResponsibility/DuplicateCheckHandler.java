package ChainOfResponsibility;

import Factory.Complaint;
import Singleton.IncidentManager;

public class DuplicateCheckHandler extends Handler {
    @Override
    public void handle(Complaint complaint) {
        IncidentManager incidentManager = IncidentManager.getInstance();

        if (incidentManager.isDuplicate(complaint)) {
            complaint.setDuplicate(true);
            System.out.println("Duplicate found for complaint #" + complaint.getId());
            return;
        }

        incidentManager.addComplaint(complaint);
        System.out.println("No duplicate found for complaint #" + complaint.getId());
        handleNext(complaint);
    }
}
