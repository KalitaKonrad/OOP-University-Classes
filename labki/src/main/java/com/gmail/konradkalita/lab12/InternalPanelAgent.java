package com.gmail.konradkalita.lab12;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InternalPanelAgent extends Thread {

  BlockingQueue<InternalCall> input = new ArrayBlockingQueue<>(100);
  ElevatorCar elevatorCar;

  static class InternalCall {
    private final int toFloor;

    InternalCall(int toFloor) {
      this.toFloor = toFloor;
    }
  }

  InternalPanelAgent(ElevatorCar elevatorCar) {
    this.elevatorCar = elevatorCar;
  }

  @SneakyThrows
  public void run() {
    for (; ; ) {
      InternalCall ic = input.take();
      int currentFloor = elevatorCar.getFloor();
      if (currentFloor < ic.toFloor) {
        elevatorCar.movementState = ElevatorCar.Movement.MOVING;
        elevatorCar.tour = ElevatorCar.Tour.UP;
        elevatorCar.floor = ic.toFloor; // ???
        ElevatorStops.get().setLiftStopUp(ic.toFloor);
      } else if (currentFloor > ic.toFloor) {
        elevatorCar.movementState = ElevatorCar.Movement.MOVING;
        elevatorCar.tour = ElevatorCar.Tour.DOWN;
        elevatorCar.floor = ic.toFloor;
        ElevatorStops.get().setLiftStopDown(ic.toFloor);
      }
    }
  }
}
