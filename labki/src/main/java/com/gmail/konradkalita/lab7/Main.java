package com.gmail.konradkalita.lab7;

import com.gmail.konradkalita.lab6.ColumnNotFoundException;
import com.gmail.konradkalita.lab6.EmptyColumnValueException;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        AdminUnitList list = new AdminUnitList();
        String filePath = getClass().getResource("/admin-units.csv").getPath();

        try {
            list.read(filePath);
            System.out.println(list.get(4450).toString());
            AdminUnitList n = list.getNeighbours(list.get(4450), 10.0);
            n.units.forEach(System.out::println);
//            list.units.stream()
//                    .filter(unit -> unit.adminLevel == 4)
//                    .forEach(unit -> System.out);

        } catch (IOException | EmptyColumnValueException | ColumnNotFoundException e) {
            e.printStackTrace();
        }
    }
}
