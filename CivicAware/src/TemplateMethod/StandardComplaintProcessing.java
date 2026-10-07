package TemplateMethod;

import Factory.Complaint;

public class StandardComplaintProcessing extends ComplaintProcessingTemplate {
    @Override
    protected void afterProcessing(Complaint complaint) {
        System.out.println("Complaint #" + complaint.getId() + " processing completed.");
    }
}
