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

    System.out.println("=== 1 ===");
    makeInternalCall(4);
    makeInternalCall(7);
    makeExternalCall(6, false);
    Thread.sleep(10000);
    makeInternalCall(1);
    makeExternalCall(3, true);

    //    System.out.println("=== 2 ===");
    //    makeExternalCall(2, true);
    //    makeExternalCall(3, false);
    //    makeExternalCall(8, true);

    //    System.out.println("=== 3 ===");
    //    makeExternalCall(1, true);
    //    Thread.sleep(100);
    //    makeInternalCall(7);
    //    Thread.sleep(100);
    //    makeExternalCall(5,true);
    //    makeExternalCall(6,false);
    //    makeInternalCall(2);
  }
}
