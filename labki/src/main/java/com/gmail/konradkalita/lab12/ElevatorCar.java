package com.gmail.konradkalita.lab12;

import lombok.SneakyThrows;

public class ElevatorCar extends Thread {
  int floor = 0;
  Movement movementState = Movement.STOP;
  Tour tour = Tour.UP;

  public int getFloor() {
    return floor;
  }

  enum Tour {
    UP,
    DOWN
  };

  enum Movement {
    STOP,
    MOVING
  };

  public void run() {
    for (; ; ) {
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (movementState == Movement.STOP && tour == Tour.DOWN) {
        if (!ElevatorStops.get().hasStopBelow(floor)) tour = Tour.UP;
        else movementState = Movement.MOVING;
        continue;
      }

      if (movementState == Movement.STOP && tour == Tour.UP) {
        if (!ElevatorStops.get().hasStopAbove(floor)) tour = Tour.DOWN;
        else movementState = Movement.MOVING;
        continue;
      }

      if (movementState == Movement.MOVING && tour == Tour.DOWN) {
        if (floor > ElevatorStops.get().getMinSetFloor()) {
          floor--;
          System.out.println("Floor" + floor);
        } else {
          movementState = Movement.STOP;
          tour = Tour.UP;
        }

        if (ElevatorStops.get().whileMovingDownShouldStopAt(floor)
            || floor == ElevatorStops.get().getMinSetFloor()) {
          movementState = Movement.STOP;
          ElevatorStops.get().clearStopDown(floor);
          System.out.println("STOP");
        }
        continue;
      }

      if (movementState == Movement.MOVING && tour == Tour.UP) {
        if (floor < ElevatorStops.get().getMaxSetFloor()) {
          floor++;
          System.out.println("Floor" + floor);
        } else {
          movementState = Movement.STOP;
          tour = Tour.DOWN; // or Tour.UP?
        }

        if (ElevatorStops.get().whileMovingUpShouldStopAt(floor)
            || floor == ElevatorStops.get().getMaxSetFloor()) {
          movementState = Movement.STOP;
          ElevatorStops.get().clearStopUp(floor);
          System.out.println("STOP");
        }
        continue;
      }
    }
  }
}
