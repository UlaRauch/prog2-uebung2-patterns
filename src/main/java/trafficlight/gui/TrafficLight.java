package trafficlight.gui;


import trafficlight.Observer.Observer;

import java.awt.*;

public class TrafficLight extends Light implements Observer {

    TrafficLight(Color color) {
        super(color);
    }//geerbt von Light: Color on = this <- color argument

    public void turnOn(boolean a) {
        isOn = a;
        repaint();
    }

    public boolean isOn() {
        return isOn;
    }

    //TODO implement a part of the pattern here

    //toggle color in TrafficLight
    @Override
    public void update(Object o) {
        turnOn(!this.isOn());
    }
}
