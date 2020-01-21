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
      // 10 ... 20 -> 80 ... 90 ->
      int[] subarray = subArray(array, array.length - end, array.length - start);
      int counter = 0;
      for (int i = array.length - end; i < array.length - start; i++) {
        array[i] = array[end - counter - 1];
        counter++;
      }

      counter = 0;
      // 10 ... 20 <- 80 ... 90
      for (int i = start; i < end; i++) {
        array[i] = subarray[subarray.length - 1 - counter];
        counter++;
      }

      semaphore.release(); // zdejmuje z semafora
    }
  }

  static void parrarelReverse(int threadCount) throws InterruptedException {
    ArrayReverse[] threads = new ArrayReverse[threadCount];
    int threadArraySize = array.length / threadCount;
    System.out.println();
    semaphore.acquire(threadCount); // nakladam semafor na ilosc threadCount watkow

    for (int i = 0; i < threadCount; i++) {
      threads[i] = new ArrayReverse(i * threadArraySize, (i + 1) * threadArraySize);
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
    parrarelReverse(threadCount);

    //    for (int i = 0; i < array.length; i++) {
    //      System.out.println(array[i]);
    //    }
    //    for (int i = 0; i < array.length; i++) {
    //      System.out.println(newTestArray[i]);
    //    }

    System.out.println(test(newTestArray, array));
  }
}
