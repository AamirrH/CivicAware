package ChainOfResponsibility;

import Factory.Complaint;
import Factory.ComplaintType;
import Factory.SeverityLevel;

import java.util.Locale;

public class SeverityAnalysisHandler extends Handler {
    @Override
    public void handle(Complaint complaint) {
        int riskScore = 0;
        String details = (complaint.getDescription() + " " + complaint.getLocation())
                .toLowerCase(Locale.ROOT);

        if (complaint.getType() == ComplaintType.OPEN_MANHOLE) {
            riskScore += 40;
        }
        if (details.contains("school")) {
            riskScore += 30;
        }
        if (details.contains("heavy traffic")) {
            riskScore += 20;
        }
        if (complaint.getType() == ComplaintType.ROAD_FLOODING
                || details.contains("rain")) {
            riskScore += 10;
        }

        riskScore = Math.min(riskScore, 100);
        complaint.setRiskScore(riskScore);
        complaint.setSeverity(SeverityLevel.fromRiskScore(riskScore));

        System.out.println("Risk score: " + riskScore
                + " (" + complaint.getSeverity() + ")");
        handleNext(complaint);
    }
}
