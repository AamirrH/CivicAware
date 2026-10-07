package Strategy;

import Factory.Complaint;
import Factory.ComplaintType;
import java.util.Locale;

public class DefaultSeverityStrategy implements SeverityStrategy {
    @Override
    public int calculateRiskScore(Complaint complaint) {
        int riskScore = 0;
        String details = (complaint.getDescription() + " " + complaint.getLocation()).toLowerCase(Locale.ROOT);

        if (complaint.getType() == ComplaintType.OPEN_MANHOLE) riskScore += 40;
        if (details.contains("school")) riskScore += 30;
        if (details.contains("heavy traffic")) riskScore += 20;
        if (complaint.getType() == ComplaintType.ROAD_FLOODING || details.contains("rain")) riskScore += 10;

        return Math.min(riskScore, 100);
    }
}
