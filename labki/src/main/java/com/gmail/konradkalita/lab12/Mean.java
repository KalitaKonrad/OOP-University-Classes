package com.gmail.konradkalita.lab12;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class Mean {
  static double[] array;

  static void initArray(int size) {
    array = new Random().doubles(size, 1, 100).toArray();
  }

  public static class MeanCalc extends Thread {
    private final int start;
    private final int end;
    private double mean = 0;

    MeanCalc(int start, int end) {
      this.start = start;
      this.end = end;
    }

    private double computeAverage(double[] array) {
      return Arrays.stream(array).average().getAsDouble();
    }

    public void run() {
      double[] subArray = subArray(array, this.start, this.end);
      this.mean = computeAverage(subArray);
      System.out.printf(Locale.US, "%d-%d mean=%f\n", start, end, mean);
    }
  }

  public static double[] subArray(double[] array, int beg, int end) {
    return Arrays.copyOfRange(array, beg, end + 1);
  }

  static void parallelMean(int threadCount) {

    MeanCalc[] threads = new MeanCalc[threadCount];
    int threadArraySize = array.length / threadCount;

    for (int i = 0; i < threadCount; i++) {
      threads[i] = new MeanCalc(i * threadArraySize, (i + 1) * threadArraySize);
    }
    double t1 = System.nanoTime() / 1e6;
    Arrays.stream(threads).forEach(MeanCalc::start);
    double t2 = System.nanoTime() / 1e6;

    for (MeanCalc thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    double mean = Arrays.stream(threads).mapToDouble(thread -> thread.mean).average().getAsDouble();
    double t3 = System.nanoTime() / 1e6;
    System.out.printf(
        Locale.US,
        "size = %d threadCount=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
        array.length,
        threadCount,
        t2 - t1,
        t3 - t1,
        mean);
  }

  public static void main(String[] args) {
    initArray(100000000);
    parallelMean(1000);
    parallelMean(10);
  }
}
