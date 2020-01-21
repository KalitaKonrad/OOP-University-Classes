package com.gmail.konradkalita.kolos;

import com.gmail.konradkalita.lab6.CSVReader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Police {
  CSVReader reader;
  List<PoliceUnit> policeUnits = new ArrayList<>();

  public Police() {
    try {
      //      String filePath = this.getClass().getResource("/policja.csv").getPath();
      //      System.out.println(filePath);
      this.reader = new CSVReader(this.getClass().getResource("/policja.csv").getPath(), ";", true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void readData() {
    while (reader.next()) {
      PoliceUnit policeUnit =
          new PoliceUnit(
              reader.getInt(0, -1),
              reader.getInt(1, -1),
              reader.get(2, ""),
              reader.get(3, ""),
              reader.get(4, ""),
              reader.get(5, ""),
              reader.get(6, ""),
              reader.get(7, ""),
              reader.get(8, ""),
              reader.get(9, ""),
              reader.get(10, ""),
              reader.get(11, ""));
      policeUnits.add(policeUnit);
    }
  }

  public List<PoliceUnit> task1() {
    return policeUnits.stream()
        .sorted(
            Comparator.comparing((PoliceUnit unit) -> unit.miasto)
                .thenComparing(unit -> unit.ulica))
        .collect(Collectors.toList());
  }

  public List<PoliceUnit> task2() {
    return policeUnits.stream()
        .filter(unit -> !unit.internet.equals(""))
        .sorted(Comparator.comparing((PoliceUnit unit) -> unit.miasto))
        .collect(Collectors.toList());
  }

  public List<PoliceUnit> task3() {
    return policeUnits.stream().filter(unit -> unit.fax.equals("")).collect(Collectors.toList());
  }

  public Map<String, Integer> task4() {
    Map<String, Integer> output = new HashMap<>();
    policeUnits.forEach(
        unit -> {
          int count = output.getOrDefault(unit.rodzaj, 0);
          output.put(unit.rodzaj, count + 1);
        });
    return output;
  }

  public static void main(String[] args) {
    Police police = new Police();
    police.readData();

    System.out.println("===== 1 ======");
    police.task1().forEach(System.out::println);
    System.out.println("===== 2 ======");
    police.task2().forEach(System.out::println);
    System.out.println("===== 3 ======");
    police.task3().forEach(System.out::println);
    System.out.println("===== 4 ======");
    Map<String, Integer> occurences = police.task4();
    occurences.forEach((key, value) -> System.out.println(key + " occured: " + value + " times"));
  }
}
