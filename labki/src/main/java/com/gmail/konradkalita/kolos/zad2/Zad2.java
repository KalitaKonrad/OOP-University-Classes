package com.gmail.konradkalita.kolos.zad2;

import com.gmail.konradkalita.lab12.Mean;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.Semaphore;

public class Zad2 {
  static int[] array;
  static Semaphore semaphore = new Semaphore(0);

  public Zad2(int elementCount) {
    array = new int[elementCount]; // fix to 100mln
    for (int i = 0; i < elementCount; i++) {
      array[i] = i;
    }
  }

  public static int[] subArray(int[] array, int beg, int end) {
    return Arrays.copyOfRange(array, beg, end + 1);
  }

  public static class ArrayReverse extends Thread {
    private final int start;
    private final int end;

    ArrayReverse(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @SneakyThrows
    public void run() {
      int counter = 0;
      for (int i = start; i < start + (end - start) / 2; i++) {
        int tmp = array[start + counter];
        array[start + counter] = array[end - counter - 1];
        array[end - counter - 1] = tmp;
        counter++;
      }
      // semaphore.release();
    }
  }

  static void parrarelReverse(int threadCount) throws InterruptedException {
    ArrayReverse[] threads = new ArrayReverse[threadCount];
    int threadArraySize = array.length / threadCount;
    // semaphore.acquire(threadCount);

    for (int i = 0; i < threadCount; i++) {
      threads[i] = new ArrayReverse(i * threadArraySize, (i + 1) * threadArraySize);
      System.out.println(i * threadArraySize + " " + (i + 1) * threadArraySize);
    }

    double t1 = System.nanoTime() / 1e6;
    Arrays.stream(threads).forEach(ArrayReverse::start);
    double t2 = System.nanoTime() / 1e6;
  }

  public static boolean test(int[] array1, int[] array2) {
    return Arrays.equals(array1, array2);
  }

  @SneakyThrows
  public static void main(String[] args) {
    int elementsCount = 100, threadCount = 10;
    int[] newTestArray = new int[elementsCount];
    for (int i = 0; i < elementsCount; i++) {
      newTestArray[i] = elementsCount - i - 1;
    }
    Zad2 zad2 = new Zad2(elementsCount);
    zad2.parrarelReverse(threadCount);

    for (int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    }
    System.out.println(test(newTestArray, array));
  }
}
