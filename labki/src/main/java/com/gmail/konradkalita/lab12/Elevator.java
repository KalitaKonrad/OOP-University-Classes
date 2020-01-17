package com.gmail.konradkalita.lab12;

public class Elevator {
  static ElevatorCar car = new ElevatorCar();
  static ExternalPanelsAgent externalPanelAgent = new ExternalPanelsAgent(car);
  static InternalPanelAgent internalPanelAgent = new InternalPanelAgent(car);

  static void makeExternalCall(int atFloor, boolean directionUp) {
    try {
      externalPanelAgent.input.put(new ExternalPanelsAgent.ExternalCall(atFloor, directionUp));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static void makeInternalCall(int toFloor) {
    try {
      internalPanelAgent.input.put(new InternalPanelAgent.InternalCall(toFloor));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static void init() {
    car.start();
    externalPanelAgent.start();
    internalPanelAgent.start();
  }

  public static void main(String[] args) throws InterruptedException {
    init();
    makeExternalCall(2, false);
    Thread.sleep(100);
    makeExternalCall(3, false);

    //    Thread.sleep(100);
    //    makeInternalCall(2);
    //    makeExternalCall(0, false);
  }
}
