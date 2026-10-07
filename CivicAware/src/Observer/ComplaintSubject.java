package Observer;

import Factory.ComplaintStatus;

import java.util.ArrayList;
import java.util.List;

public class ComplaintSubject {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(long complaintId, ComplaintStatus status) {
        for (Observer observer : new ArrayList<>(observers)) {
            observer.update(complaintId, status);
        }
    }
}
