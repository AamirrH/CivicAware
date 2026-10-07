# Smart Civic Issue Response & Escalation System

This file is being completed one design pattern at a time so that each part can be
reviewed and committed separately.

## 1. Factory Pattern — Complaint Creation

### Problem

The system supports several civic complaint types. If the client creates each
concrete complaint with `new`, it must know every implementation class and would
need repeated selection logic.

### Solution

The client gives a `ComplaintType` to `ComplaintFactory`. `CivicComplaintFactory`
contains the object-creation decision and returns the appropriate `Complaint`
subclass.

### Classes involved

- Product: `Complaint`
- Concrete products: `PotholeComplaint`, `WaterLeakageComplaint`,
  `GarbageComplaint`, `StreetlightComplaint`, `TrafficSignalComplaint`,
  `OpenManholeComplaint`, and `RoadFloodingComplaint`
- Factory interface: `ComplaintFactory`
- Concrete factory: `CivicComplaintFactory`
- Selection enum: `ComplaintType`

### Where it is used

`Main` asks the factory to create complaints. It never directly calls a concrete
complaint constructor.

### Simple viva explanation

The Factory Pattern puts object creation in one place. The client requests a
complaint by type and receives the correct object without knowing which concrete
class must be instantiated. Adding a complaint type requires changing the factory,
not every client.

## 2. Abstract Factory Pattern — Department Resources

### Problem

After a complaint is routed to a department, the system needs a matching team and
service. Creating these separately could accidentally combine products from
different departments, such as a water team with a road service.

### Solution

`DepartmentFactory` defines methods for creating both related products. Each
concrete department factory returns a team and service belonging to the same
department family.

### Classes involved

- Abstract factory: `DepartmentFactory`
- Concrete factories: `RoadDepartmentFactory`, `WaterDepartmentFactory`,
  `SanitationDepartmentFactory`, `ElectricalDepartmentFactory`, and
  `TrafficDepartmentFactory`
- Abstract products: `Team` and `Service`
- Concrete teams: `RoadRepairTeam`, `WaterRepairTeam`, `SanitationTeam`,
  `ElectricalTeam`, and `TrafficTeam`
- Concrete services: `RoadRepairService`, `WaterRepairService`,
  `SanitationService`, `ElectricalService`, and `TrafficService`

### Where it is used

After the responsible department is known, the matching factory creates the team
and service needed for that complaint. `Main` demonstrates all five product
families through the common interfaces.

### Simple viva explanation

The Abstract Factory Pattern creates a family of related objects. A road factory
always supplies a road team and road service, while a water factory supplies a
water team and water service. The client uses `Team` and `Service` interfaces and
does not need to know their concrete classes.

## 3. Chain of Responsibility Pattern — Complaint Processing

### Problem

A complaint must pass through several processing steps in a fixed order. Putting
all validation, risk, routing, assignment, and escalation logic in one class would
make that class large and difficult to understand or change.

### Solution

Each processing step is a separate `Handler`. A handler performs only its own job
and then passes the complaint to the next handler. Validation or duplicate checks
can stop the chain when further processing is not appropriate.

The configured chain is:

`ValidationHandler` → `DuplicateCheckHandler` → `SeverityAnalysisHandler` →
`DepartmentRoutingHandler` → `TeamAssignmentHandler` → `EscalationHandler`

### Classes involved

- Base handler: `Handler`
- Concrete handlers: `ValidationHandler`, `DuplicateCheckHandler`,
  `SeverityAnalysisHandler`, `DepartmentRoutingHandler`,
  `TeamAssignmentHandler`, and `EscalationHandler`
- Chain builder: `ComplaintProcessingChain`
- Supporting values: `ComplaintStatus`, `SeverityLevel`, `DepartmentType`, and
  `EscalationLevel`

### Where it is used

`ComplaintProcessingChain.createChain()` builds the processing pipeline. The
client sends a complaint only to the first handler. Department routing and team
assignment reuse the Abstract Factory implementation from Pattern 2.

### Risk and severity rules

- Open manhole: +40
- Near a school: +30
- Heavy traffic: +20
- Road flooding or rain: +10
- 0–30 is `LOW`, 31–60 is `MEDIUM`, 61–80 is `HIGH`, and 81–100 is `CRITICAL`

The resolution deadlines are 48, 24, 12, and 4 hours respectively. When an
unresolved complaint is overdue, each escalation check advances it from team
member to supervisor, department head, and finally admin.

### Simple viva explanation

The Chain of Responsibility passes one complaint through small handlers in order.
Each handler has one responsibility and knows only the next handler. This avoids a
large processing class and lets a step be changed without rewriting the entire
pipeline.

## 4. Observer Pattern — Automatic Status Updates

### Problem

Citizens, administrators, and assigned teams all need updates when a complaint's
status changes. The complaint should not contain separate notification calls for
every interested person.

### Solution

Observers register with the complaint's `ComplaintSubject`. Whenever
`Complaint.setStatus()` changes to a different status, the subject automatically
calls `update()` on every registered observer.

### Classes involved

- Observer interface: `Observer`
- Concrete observers: `CitizenObserver`, `AdminObserver`, and `TeamObserver`
- Subject: `ComplaintSubject`
- Observed object: `Complaint`

### Where it is used

The citizen and admin observers are registered when a complaint is created. During
team assignment, `TeamAssignmentHandler` registers an observer for the actual team
before changing the status to `ASSIGNED`. Later changes such as `IN_PROGRESS`,
`RESOLVED`, or `ESCALATED` notify all registered observers automatically.

### Simple viva explanation

The Observer Pattern creates a one-to-many relationship. A complaint is the thing
being watched, and the citizen, admin, and team are watchers. When the complaint
status changes once, the subject sends the same update to every watcher without
the complaint needing to know how each watcher responds.

## 5. Bridge Pattern — Notification Channels

### Problem

The system has different notification purposes for citizens, admins, and teams,
and it also has different delivery channels such as SMS, email, and push. Creating
a separate class for every possible purpose-and-channel combination would produce
many nearly identical classes.

### Solution

The Bridge Pattern separates the notification abstraction from the sender
implementation. A `Notification` contains a `NotificationSender`, so either side
can change independently.

Examples include:

- `CitizenNotification` with `SmsSender`
- `CitizenNotification` with `EmailSender`
- `AdminNotification` with `EmailSender`
- `TeamNotification` with `PushSender`

### Classes involved

- Abstraction: `Notification`
- Refined abstractions: `CitizenNotification`, `AdminNotification`, and
  `TeamNotification`
- Implementor: `NotificationSender`
- Concrete implementors: `SmsSender`, `EmailSender`, and `PushSender`

### Where it is used

The Observer classes use a supplied `Notification` when they receive a complaint
status update. The client chooses the channel when assembling each observer.
`ComplaintProcessingChain` also accepts the sender used for assigned-team
notifications. No delivery channel is hard-coded in `Complaint`.

### Simple viva explanation

The Bridge Pattern connects two independent class hierarchies using composition.
The notification class decides who the message is for, while the sender decides
how it is delivered. For example, the same citizen notification works with either
an SMS sender or an email sender without creating a new combined subclass.

## 6. Singleton Pattern — Central Incident Manager

### Problem

If different modules create separate incident managers, each manager could hold a
different complaint list. Status updates, duplicate checks, retrieval, and
escalation information would then disagree.

### Solution

`IncidentManager` has a private constructor and one eagerly created static
instance. Every caller obtains that same object through `getInstance()`.

### Classes involved

- Singleton: `IncidentManager`
- Centrally managed object: `Complaint`

### Where it is used

`DuplicateCheckHandler` obtains the singleton to check and register complaints.
Clients use the same singleton to retrieve complaints, update their status, list
all incidents, list escalated incidents, and inspect escalation information.

### Simple viva explanation

The Singleton Pattern guarantees that only one `IncidentManager` exists in the
application. Its constructor is private, so other classes cannot use `new`.
`getInstance()` always returns the same stored object, giving the whole system one
shared source of complaint data.
