package ChainOfResponsibility;

import Factory.Complaint;
import Factory.SeverityLevel;
import Strategy.DefaultSeverityStrategy;
import Strategy.SeverityStrategy;

public class SeverityAnalysisHandler extends Handler {
    private final SeverityStrategy severityStrategy;

    public SeverityAnalysisHandler() {
        this(new DefaultSeverityStrategy());
    }

    public SeverityAnalysisHandler(SeverityStrategy severityStrategy) {
        if (severityStrategy == null) throw new IllegalArgumentException("Severity strategy cannot be null");
        this.severityStrategy = severityStrategy;
    }

    @Override
    public void handle(Complaint complaint) {
        int riskScore = Math.min(severityStrategy.calculateRiskScore(complaint), 100);
        complaint.setRiskScore(riskScore);
        complaint.setSeverity(SeverityLevel.fromRiskScore(riskScore));

        System.out.println("Risk score: " + riskScore + " (" + complaint.getSeverity() + ")");
        handleNext(complaint);
    }
}
