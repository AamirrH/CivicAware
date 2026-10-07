import AbstractFactory.DepartmentFactory;
import AbstractFactory.ElectricalDepartmentFactory;
import AbstractFactory.RoadDepartmentFactory;
import AbstractFactory.SanitationDepartmentFactory;
import AbstractFactory.Service;
import AbstractFactory.Team;
import AbstractFactory.TrafficDepartmentFactory;
import AbstractFactory.WaterDepartmentFactory;
import Bridge.AdminNotification;
import Bridge.CitizenNotification;
import Bridge.EmailSender;
import Bridge.Notification;
import Bridge.PushSender;
import Bridge.SmsSender;
import Bridge.TeamNotification;
import Builder.ComplaintBuilder;
import ChainOfResponsibility.ComplaintProcessingChain;
import ChainOfResponsibility.Handler;
import Factory.CivicComplaintFactory;
import Factory.Complaint;
import Factory.ComplaintFactory;
import Factory.ComplaintStatus;
import Factory.ComplaintType;
import Memento.ComplaintMemento;
import Mediator.AdminColleague;
import Mediator.CitizenColleague;
import Mediator.ComplaintMediator;
import Mediator.TeamColleague;
import Observer.AdminObserver;
import Observer.CitizenObserver;
import Prototype.ComplaintPrototype;
import Singleton.IncidentManager;
import Strategy.SafetySeverityStrategy;
import TemplateMethod.StandardComplaintProcessing;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("SMART CIVIC ISSUE RESPONSE & ESCALATION SYSTEM");

        demonstrateComplaintFactory();
        demonstrateDepartmentAbstractFactory();
        demonstrateChainOfResponsibility();
        demonstrateObserverPattern();
        demonstrateBridgePattern();
        demonstrateSingletonPattern();

        demonstrateBuilderPattern();
        demonstratePrototypePattern();
        demonstrateStrategyPattern();
        demonstrateMementoPattern();
        demonstrateTemplateMethodPattern();
        demonstrateMediatorPattern();
    }

    private static void demonstrateComplaintFactory() {
        ComplaintFactory complaintFactory = new CivicComplaintFactory();
        System.out.println("\n1. Factory Pattern demonstration");

        long complaintId = 1001L;
        for (ComplaintType type : ComplaintType.values()) {
            Complaint complaint = complaintFactory.createComplaint(
                    type, complaintId++, "Citizen reported a " + type + " issue", "Central Ward");
            System.out.println("Created " + complaint.getClass().getSimpleName()
                    + " for complaint #" + complaint.getId());
        }
    }

    private static void demonstrateDepartmentAbstractFactory() {
        System.out.println("\n2. Abstract Factory Pattern demonstration");

        DepartmentFactory[] departmentFactories = {
                new RoadDepartmentFactory(), new WaterDepartmentFactory(),
                new SanitationDepartmentFactory(), new ElectricalDepartmentFactory(),
                new TrafficDepartmentFactory()
        };

        long complaintId = 2001L;
        for (DepartmentFactory factory : departmentFactories) {
            Team team = factory.createTeam();
            Service service = factory.createService();
            System.out.println("Created family: " + team.getTeamName() + " + " + service.getServiceName());
            team.assignComplaint(complaintId);
            service.performService(complaintId++);
        }
    }

    private static void demonstrateChainOfResponsibility() {
        System.out.println("\n3. Chain of Responsibility Pattern demonstration");
        ComplaintFactory complaintFactory = new CivicComplaintFactory();
        Complaint complaint = complaintFactory.createComplaint(
                ComplaintType.OPEN_MANHOLE, 3001L,
                "Dangerous open manhole near a school with heavy traffic", "University Road");

        Handler processingChain = ComplaintProcessingChain.createChain();
        processingChain.handle(complaint);
        System.out.println("Processing result: " + complaint);

        Complaint duplicate = complaintFactory.createComplaint(
                ComplaintType.OPEN_MANHOLE, 3002L,
                "Dangerous open manhole near a school with heavy traffic", "University Road");
        processingChain.handle(duplicate);

        System.out.println("Simulating a missed deadline for the demo...");
        complaint.setResolutionDeadline(LocalDateTime.now().minusMinutes(1));
        ComplaintProcessingChain.checkEscalation(complaint);
    }

    private static void demonstrateObserverPattern() {
        System.out.println("\n4. Observer Pattern demonstration");
        ComplaintFactory complaintFactory = new CivicComplaintFactory();
        Complaint complaint = complaintFactory.createComplaint(
                ComplaintType.WATER_LEAKAGE, 4001L,
                "Water pipe leaking beside the public library", "Library Road");

        complaint.addObserver(new CitizenObserver("Aarav", new CitizenNotification(new SmsSender())));
        complaint.addObserver(new AdminObserver("Central Control Room", new AdminNotification(new EmailSender())));

        ComplaintProcessingChain.createChain().handle(complaint);
        complaint.setStatus(ComplaintStatus.IN_PROGRESS);
        complaint.setStatus(ComplaintStatus.RESOLVED);
    }

    private static void demonstrateBridgePattern() {
        System.out.println("\n5. Bridge Pattern demonstration");
        Notification citizenSms = new CitizenNotification(new SmsSender());
        Notification citizenEmail = new CitizenNotification(new EmailSender());
        Notification adminEmail = new AdminNotification(new EmailSender());
        Notification teamPush = new TeamNotification(new PushSender());

        citizenSms.notifyUser("Complaint #5001 was received");
        citizenEmail.notifyUser("Complaint #5001 was validated");
        adminEmail.notifyUser("Critical complaint #5001 needs attention");
        teamPush.notifyUser("Complaint #5001 was assigned to your team");
    }

    private static void demonstrateSingletonPattern() {
        System.out.println("\n6. Singleton Pattern demonstration");
        IncidentManager firstReference = IncidentManager.getInstance();
        IncidentManager secondReference = IncidentManager.getInstance();
        System.out.println("Both references are the same instance: " + (firstReference == secondReference));

        ComplaintFactory complaintFactory = new CivicComplaintFactory();
        Complaint complaint = complaintFactory.createComplaint(
                ComplaintType.GARBAGE, 6001L,
                "Garbage overflowing beside the community park", "Park Street");

        ComplaintProcessingChain.createChain().handle(complaint);
        firstReference.addComplaint(complaint);
        System.out.println("Retrieved complaint: " + secondReference.getComplaint(6001L));
        firstReference.updateComplaintStatus(6001L, ComplaintStatus.IN_PROGRESS);
        firstReference.updateComplaintStatus(6001L, ComplaintStatus.RESOLVED);
        System.out.println("Centrally managed complaints: " + secondReference.getAllComplaints().size());
        System.out.println("Escalated complaints: " + secondReference.getEscalatedComplaints().size());
        System.out.println(secondReference.getEscalationInformation(3001L));
    }

    private static void demonstrateBuilderPattern() {
        System.out.println("\n7. Builder Pattern demonstration");
        Complaint complaint = new ComplaintBuilder()
                .setId(7001L)
                .setDescription("Pothole near college gate")
                .setLocation("College Road")
                .setType(ComplaintType.POTHOLE)
                .build();
        System.out.println("Built complaint: " + complaint);
    }

    private static void demonstratePrototypePattern() {
        System.out.println("\n8. Prototype Pattern demonstration");
        Complaint original = new ComplaintBuilder()
                .setId(8001L)
                .setDescription("Open manhole near school")
                .setLocation("University Road")
                .setType(ComplaintType.OPEN_MANHOLE)
                .build();
        ComplaintProcessingChain.createChain().handle(original);

        ComplaintPrototype prototype = new ComplaintPrototype(original);
        Complaint cloned = prototype.cloneComplaint(8002L);

        System.out.println("Original: " + original);
        System.out.println("Cloned  : " + cloned);
    }

    private static void demonstrateStrategyPattern() {
        System.out.println("\n9. Strategy Pattern demonstration");
        Complaint complaint = new ComplaintBuilder()
                .setId(9001L)
                .setDescription("Dangerous open manhole near a school")
                .setLocation("School Road")
                .setType(ComplaintType.OPEN_MANHOLE)
                .build();

        Handler chain = new ChainOfResponsibility.ValidationHandler()
                .setNext(new ChainOfResponsibility.DuplicateCheckHandler())
                .setNext(new ChainOfResponsibility.SeverityAnalysisHandler(new SafetySeverityStrategy()));
        chain.handle(complaint);
        System.out.println("Safety strategy result: " + complaint.getRiskScore()
                + " (" + complaint.getSeverity() + ")");
    }

    private static void demonstrateMementoPattern() {
        System.out.println("\n10. Memento Pattern demonstration");
        Complaint complaint = new ComplaintBuilder()
                .setId(10001L)
                .setDescription("Water leakage near market")
                .setLocation("Market Road")
                .setType(ComplaintType.WATER_LEAKAGE)
                .build();

        complaint.setStatus(ComplaintStatus.IN_PROGRESS);
        ComplaintMemento savedState = complaint.saveState();

        complaint.setStatus(ComplaintStatus.RESOLVED);
        System.out.println("Current status: " + complaint.getStatus());

        complaint.restoreState(savedState);
        System.out.println("Restored status: " + complaint.getStatus());
    }

    private static void demonstrateTemplateMethodPattern() {
        System.out.println("\n11. Template Method Pattern demonstration");
        Complaint complaint = new ComplaintBuilder()
                .setId(11001L)
                .setDescription("Garbage overflowing near park")
                .setLocation("Park Street")
                .setType(ComplaintType.GARBAGE)
                .build();

        StandardComplaintProcessing processor = new StandardComplaintProcessing();
        processor.processComplaint(complaint);
    }

    private static void demonstrateMediatorPattern() {
        System.out.println("\n12. Mediator Pattern demonstration");
        Complaint complaint = new ComplaintBuilder()
                .setId(12001L)
                .setDescription("Traffic signal not working")
                .setLocation("Main Junction")
                .setType(ComplaintType.TRAFFIC_SIGNAL)
                .build();

        ComplaintMediator mediator = new ComplaintMediator();
        CitizenColleague citizen = new CitizenColleague("Huzaif");
        AdminColleague admin = new AdminColleague();
        TeamColleague team = new TeamColleague("Traffic Team");

        mediator.register(citizen);
        mediator.register(admin);
        mediator.register(team);

        citizen.report("New complaint reported", complaint);
        admin.updateTeam("Please inspect the traffic signal", complaint);
    }
}
