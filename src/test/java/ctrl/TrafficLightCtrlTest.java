package ctrl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import trafficlight.ctrl.TrafficLightCtrl;
import trafficlight.gui.TrafficLight;
import trafficlight.gui.TrafficLightGui;

import static org.junit.jupiter.api.Assertions.*;

public class TrafficLightCtrlTest {

    TrafficLightCtrl testctrl;

    @BeforeEach
            public void createTestCtrl() {
        testctrl = TrafficLightCtrl.getInstance();
    }


    @Test
    @DisplayName("Does TrafficLightCtrl Object start with greenState")
    public void testTrafficLightCtrlStartGreenState () {
        testctrl.resetCtrl();
        assertEquals(testctrl.getGreenState(), testctrl.getCurrentState());
    }

    @Test
    @DisplayName("Does getInstance() method return the same TrafficLightCtrl object")
    public void testTrafficLightCtrlSameInstance () {
        TrafficLightCtrl testctrl1 = TrafficLightCtrl.getInstance();
        TrafficLightCtrl testctrl2 = TrafficLightCtrl.getInstance();
        assertSame(testctrl1, testctrl2);
    }


    @Test
    @DisplayName("Does yellowState follow greenState")
    public void testTrafficLightCtrlYellowAfterGreen () {

        while (testctrl.getPreviousState() == testctrl.getGreenState()) {
            assertSame(testctrl.getCurrentState(), testctrl.getYellowState());
        }
    }

}