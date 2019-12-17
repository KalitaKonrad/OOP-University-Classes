package com.gmail.konradkalita.lab11;

import java.time.LocalTime;

public class Clock extends Thread{

    @Override
    public void run() {
    }

    public static void main(String[] args) throws InterruptedException {
        new Clock().start();
        while(true) {
            LocalTime time = LocalTime.now();
            System.out.printf("%02d:%02d:%02d\n",
                    time.getHour(),
                    time.getMinute(),
                    time.getSecond());
            Thread.sleep(1000);
        }

    }
}
