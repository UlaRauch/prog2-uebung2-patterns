package gui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import trafficlight.ctrl.TrafficLightCtrl;
import trafficlight.gui.TrafficLight;
import trafficlight.states.State;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrafficLightTest {

    @Test
    @DisplayName("Is green light on when notified once")
    public void testTrafficLightCtrlIsGreenLightOn () {
        TrafficLightCtrl testctrl = TrafficLightCtrl.getInstance();
        TrafficLight testgreen = new TrafficLight(Color.green);
        State testState = testctrl.getGreenState();
        testState.addObserver(testgreen);
        testState.notifyObservers();
        assertTrue(testgreen.isOn());
        testState.removeObserver(testgreen);
    }

    @Test
    @DisplayName("Is green Light off when notified twice")
    public void testTrafficLightCtrlIsGreenLightOff () {
        TrafficLightCtrl testctrl = TrafficLightCtrl.getInstance();
        TrafficLight testgreen = new TrafficLight(Color.green);
        State testState = testctrl.getGreenState();
        testState.addObserver(testgreen);
        testState.notifyObservers();
        testState.notifyObservers();
        assertFalse(testgreen.isOn());
        testState.removeObserver(testgreen);
    }

}
