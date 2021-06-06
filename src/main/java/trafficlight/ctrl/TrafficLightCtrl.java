package trafficlight.ctrl;

import trafficlight.gui.TrafficLightGui;
import trafficlight.states.State;

public class TrafficLightCtrl {

    //für Singleton
    private static TrafficLightCtrl instance = null;

    private State greenState;

    private State redState;

    private State yellowState;

    private State currentState;

    private State previousState;

    private final TrafficLightGui gui;

    private boolean doRun = true;

    //private Singleton constructor
    private TrafficLightCtrl() {
        super();
        initStates(); //setzt currentState auf greenState und previousState auf yellowState
        gui = new TrafficLightGui(this);
        gui.setVisible(true);
        //TODO useful to update the current state
        currentState.notifyObservers();
    }

    public static TrafficLightCtrl getInstance() {
        if (instance == null) {
            instance = new TrafficLightCtrl();
        }
        return instance;
    }

    public void resetCtrl() {
        currentState = greenState;
        currentState.notifyObservers();
        previousState = yellowState;
        previousState.notifyObservers();

    }

    private void initStates() {
        greenState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;
                //TODO useful to update the current state and the old one
                //siehe nextState()
                return yellowState;
            }
            @Override
            public String getColor() {
                return "green";
            }
        };

        redState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;
                //TODO useful to update the current state and the old one
                //siehe nextState()
                return yellowState;
            }
            @Override
            public String getColor() {
                return "red";
            }
        };

        yellowState = new State() {
            @Override
            public State getNextState() {
                if (previousState.equals(greenState)) {
                    previousState = currentState;
                    //TODO useful to update the current state and the old one
                    //siehe nextState()
                    return redState;
                }else {
                    previousState = currentState;
                    //TODO useful to update the current state and the old one
                    //siehe nextState()
                    return greenState;
                }
            }
            @Override
            public String getColor() {
                return "yellow";
            }
        };
        currentState = greenState; //init: beginnt mit green
        previousState = yellowState;
    }

    public State getGreenState() {
        return greenState;
    }

    public State getRedState() {
        return redState;
    }

    public State getYellowState() {
        return yellowState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public State getPreviousState() {
        return previousState;
    }

    public TrafficLightGui getGui() {
        return gui;
    }

    public void run()  {
        int intervall = 1500;
        while (doRun) {
            try {
                Thread.sleep(intervall);
                nextState(); //wechsle die Farben mit gesetzem Intervall
            } catch (InterruptedException e) {
                gui.showErrorMessage(e);
            }
        }
        System.out.println("Stopped");
        System.exit(0);
    }

    //z.B. wenn currentState = YellowState -> YellowState.getNextState -> setzt current auf green und previous auf yellow
    public void nextState() {
        currentState = currentState.getNextState();
        currentState.notifyObservers();
        previousState.notifyObservers();
        //ist das ok hier statt oben?
    }

    public void stop() {
        doRun = false;
    }
}