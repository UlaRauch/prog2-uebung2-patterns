package trafficlight.Observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observerList = new ArrayList<>();

    public void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observerList.add(observer);
    }

    public void notifyObservers() {
        for (Observer o : observerList) {
            o.update(this);
        }
    }
}
