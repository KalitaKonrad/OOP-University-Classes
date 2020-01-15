package com.gmail.konradkalita.lab12;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
  static double[] array;
  static BlockingQueue<Double> results = new ArrayBlockingQueue<>(100);

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

    @SneakyThrows
    public void run() {
      double[] subArray = subArray(array, this.start, this.end);
      this.mean = computeAverage(subArray);
      results.put(this.mean);
    }
  }

  public static double[] subArray(double[] array, int beg, int end) {
    return Arrays.copyOfRange(array, beg, end + 1);
  }

  static void parallelMean(int threadCount) throws InterruptedException {
    MeanCalc[] threads = new MeanCalc[threadCount];
    int threadArraySize = array.length / threadCount;

    for (int i = 0; i < threadCount; i++) {
      threads[i] = new MeanCalc(i * threadArraySize, (i + 1) * threadArraySize);
    }

    double t1 = System.nanoTime() / 1e6;
    Arrays.stream(threads).forEach(MeanCalc::start);
    double t2 = System.nanoTime() / 1e6;

    for (MeanCalc thread : threads) {
      thread.join();
    }

    double t3 = System.nanoTime() / 1e6;
    double finalMean =
        Arrays.stream(threads).mapToDouble(thread -> thread.mean).average().getAsDouble();
    System.out.printf(
        Locale.US,
        "PARALLEL MEAN -> size = %d threadCount=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
        array.length,
        threadCount,
        t2 - t1,
        t3 - t1,
        finalMean);
  }

  static void parrarelMeanV2(int threadCount) throws InterruptedException {
    double sumOfMeans = 0;
    MeanCalc[] threads = new MeanCalc[threadCount];
    int threadArraySize = array.length / threadCount;

    for (int i = 0; i < threadCount; i++) {
      threads[i] = new MeanCalc(i * threadArraySize, (i + 1) * threadArraySize);
    }
    double t1 = System.nanoTime() / 1e6;
    Arrays.stream(threads).forEach(MeanCalc::start);
    double t2 = System.nanoTime() / 1e6;

    for (int i = 0; i < threadCount; i++) {
      sumOfMeans += results.take();
    }

    double t3 = System.nanoTime() / 1e6;
    System.out.printf(
        Locale.US,
        "PARALLEL MEANV2 -> size = %d threadCount=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
        array.length,
        threadCount,
        t2 - t1,
        t3 - t1,
        sumOfMeans / threadCount);
  }

  public static void main(String[] args) throws InterruptedException {
    initArray(1000000);
    parallelMean(10);
    parrarelMeanV2(10);
  }
}
