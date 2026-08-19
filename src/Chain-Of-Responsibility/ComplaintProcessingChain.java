package ChainOfResponsibility;

import Bridge.NotificationSender;
import Bridge.PushSender;
import Factory.Complaint;

public final class ComplaintProcessingChain {
    private ComplaintProcessingChain() {
    }

    public static Handler createChain() {
        return createChain(new PushSender());
    }

    public static Handler createChain(NotificationSender teamNotificationSender) {
        Handler validation = new ValidationHandler();

        validation
                .setNext(new DuplicateCheckHandler())
                .setNext(new SeverityAnalysisHandler())
                .setNext(new DepartmentRoutingHandler())
                .setNext(new TeamAssignmentHandler(teamNotificationSender))
                .setNext(new EscalationHandler());

        return validation;
    }

    public static void checkEscalation(Complaint complaint) {
        new EscalationHandler().handle(complaint);
    }
}
