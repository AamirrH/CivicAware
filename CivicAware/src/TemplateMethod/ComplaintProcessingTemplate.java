package TemplateMethod;

import ChainOfResponsibility.ComplaintProcessingChain;
import ChainOfResponsibility.Handler;
import Factory.Complaint;

public abstract class ComplaintProcessingTemplate {
    public final void processComplaint(Complaint complaint) {
        if (complaint == null) throw new IllegalArgumentException("Complaint cannot be null");
        beforeProcessing(complaint);
        validateInput(complaint);
        processUsingExistingChain(complaint);
        afterProcessing(complaint);
    }

    protected void beforeProcessing(Complaint complaint) {
        System.out.println("Starting complaint processing: #" + complaint.getId());
    }

    protected void validateInput(Complaint complaint) {
        if (complaint.getDescription() == null || complaint.getDescription().isBlank()) {
            throw new IllegalArgumentException("Complaint description is required");
        }
    }

    protected void processUsingExistingChain(Complaint complaint) {
        Handler chain = ComplaintProcessingChain.createChain();
        chain.handle(complaint);
    }

    protected abstract void afterProcessing(Complaint complaint);
}
