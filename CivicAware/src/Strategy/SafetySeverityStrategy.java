package Strategy;

import Factory.Complaint;
import Factory.ComplaintType;
import java.util.Locale;

public class SafetySeverityStrategy implements SeverityStrategy {
    @Override
    public int calculateRiskScore(Complaint complaint) {
        int score = 0;
        String details = (complaint.getDescription() + " " + complaint.getLocation()).toLowerCase(Locale.ROOT);

        if (complaint.getType() == ComplaintType.OPEN_MANHOLE) score += 60;
        if (details.contains("school")) score += 25;
        if (details.contains("accident") || details.contains("dangerous")) score += 15;

        return Math.min(score, 100);
    }
}
