package Strategy;

import Factory.Complaint;

public interface SeverityStrategy {
    int calculateRiskScore(Complaint complaint);
}
